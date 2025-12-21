package gr.hua.dit.fitnessmanager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TCXParser {

    //TODO
    //arxikopoihsh me -1 klp an den uparxoun ta tags


    private String tcx = "no tcx name yet";
    //parsing related variable
    Document doc;

    boolean exists = false;

    //lap variables
    private LocalDateTime startTime = LocalDateTime
            .of(LocalDate.of(1, 1, 1), LocalTime.of(1, 1));
    private double totalTimeSeconds = -1;
    private double distanceMeters = -1;
    private double maximumSpeed = -1;
    private int calories = -1;

    //running 2 does not have abpm and mbpm

    private int ABPM = -1;
    private int MBPM = -1;
    private String intensity = "";
    private String triggerMethod = "";;
    private double avgSpeed = -1;

    //cadence not in biking, swimming, running - only in walking
    private int avgRunCadence = -1;
    private int maxRunCadence = -1;

    //trackpoint variables
    //private NodeList trackList;
    private Element track;// = NULL;; // = (Element) t;
    //private NodeList trackpointList; // = track.getElementsByTagName("Trackpoint");
    private Node trckpt;// = NULL;; // = trackpointList.item(k);
    private Element tp; // = NULL; // = (Element) trckpt;
    private LocalDateTime time = LocalDateTime
            .of(LocalDate.of(1, 1, 1), LocalTime.of(1, 1));
    private double latitudeDegrees = -1;
    private double longitudeDegrees = -1;
    private double altitudeMeters = -1;
    private double distanceMetersTrackpoint = -1;

    //running 2 does not have heart rate
    private int HRB = -1;

    private double speed = -1;





    public TCXParser(String tcx) {
        this.tcx = tcx;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(tcx);
        } catch (Exception e) {                                    //(ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public void parse () {
        //Node tcd = doc.getElementsByTagName("TrainingCenterDatabase");
            /*NodeList activities = doc.getElementsByTagName("Activities");
            for (int i = 0; i < activities.getLength(); i++) {
                Node act = TCD.item(i);
                if (act.getNodeType() == Node.ELEMENT_NODE) {
                    Element activity = (Element) act;

            if (activities.getNodeType() == Node.ELEMENT_NODE) {
                Element activities = (Element) activities;

            }*/

        //boolean exists = tagExists("Activity", doc);
        NodeList activityList = doc.getElementsByTagName("Activity"); //keep this in case there is more than one activity in a file
        //System.out.println(activityList.getLength());
        for (int i = 0; i < activityList.getLength(); i++) {


            Node a = activityList.item(i);
            if (a.getNodeType() == Node.ELEMENT_NODE) {
                Element act = (Element) a;
                //maybe I have to check if they are elements first before putting them in their category like string

                String sport = getTextAttr("Sport", act);
                //get id as a local date time not string

                //exists = tagExists("Id", act); //katri paei la8os edwwww
                String id = getText("Id", act);
                NodeList lapList = act.getElementsByTagName("Lap");

                parseLaps(lapList);

            }
        }
    }

    private void parseLaps (NodeList lapList) {
        for (int j = 0; j < lapList.getLength(); j++) {
            Node l = lapList.item(j);
            if (l.getNodeType() == Node.ELEMENT_NODE) {
                Element lap = (Element) l;

                String temp = getTextAttr("StartTime", lap);
                temp = temp.replace("Z", "");
                LocalDateTime startTime = LocalDateTime.parse(temp);

                totalTimeSeconds = Double.parseDouble(getText("TotalTimeSeconds", lap));
                distanceMeters = Double.parseDouble(getText("DistanceMeters", lap));
                maximumSpeed = Double.parseDouble(getText("MaximumSpeed", lap));
                calories = Integer.parseInt(getText("Calories", lap));


                //running 2 does not have abpm and mbpm
                Element el = (Element) lap.getElementsByTagName("AverageHeartRateBpm").item(0);
                if (tagExists("Value", el)) {
                    ABPM = Integer.parseInt(getText("Value", el));
                }



                el = ((Element) lap.getElementsByTagName("MaximumHeartRateBpm").item(0));
                if (tagExists("Value", el)) {
                    MBPM = Integer.parseInt(getText("Value", el));
                }

                /*MBPM = Integer.parseInt(((Element) lap.getElementsByTagName("MaximumHeartRateBpm")
                        .item(0)).getElementsByTagName("Value").item(0).getTextContent());*/

                intensity = getText("Intensity", lap);
                triggerMethod = getText("TriggerMethod", lap);

                Element extensions = (Element) ((Element) lap.getElementsByTagName("Extensions")
                        .item(lap.getElementsByTagName("Extensions").getLength() - 1)).getElementsByTagName("ns3:LX").item(0);

                avgSpeed = Double.parseDouble(getText("ns3:AvgSpeed", extensions));

                //cadence not in biking, swimming, running - only in walking
                if (tagExists("ns3:AvgRunCadence", extensions)) {
                    avgRunCadence = Integer.parseInt(getText("ns3:AvgRunCadence", extensions));
                }

                if (tagExists("ns3:MaxRunCadence", extensions)) {
                    maxRunCadence = Integer.parseInt(getText("ns3:MaxRunCadence", extensions));
                }

                NodeList trackList = lap.getElementsByTagName("Track");
                parseTracks(trackList);
            }
        }
    }

    private void parseTracks (NodeList trackList) {
        for (int z = 0; z < trackList.getLength(); z++) {
            Node t = trackList.item(z);
            if (t.getNodeType() == Node.ELEMENT_NODE) {
                Element track = (Element) t;

                NodeList trackpointList = track.getElementsByTagName("Trackpoint");
                parseTrackpoints(trackpointList);
            }
        }
    }

    private void parseTrackpoints (NodeList trackpointList) {
        for (int k = 0; k < trackpointList.getLength(); k++) {
            Node trckpt = trackpointList.item(k);
            if (trckpt.getNodeType() == Node.ELEMENT_NODE) {
                Element tp = (Element) trckpt;

                String temp = getText("Time", tp);
                temp = temp.replace("Z", "");
                time = LocalDateTime.parse(temp);

                Element position = (Element) tp.getElementsByTagName("Position").item(0);

                latitudeDegrees = Double.parseDouble(getText("LatitudeDegrees", position));
                longitudeDegrees = Double.parseDouble(getText("LongitudeDegrees", position));
                altitudeMeters = Double.parseDouble(getText("AltitudeMeters", tp));
                distanceMetersTrackpoint = Double.parseDouble(getText("DistanceMeters", tp));

                System.out.println(distanceMetersTrackpoint);

                //running 2 does not have heart rate
                Element el = (Element) tp.getElementsByTagName("HeartRateBpm").item(0);
                if (tagExists("Value", el)) {
                    HRB = Integer.parseInt(getText("Value", el));
                }


                Element extensionsTp = (Element)((Element) tp.getElementsByTagName("Extensions").item(0))
                        .getElementsByTagName("ns3:TPX").item(0);


                speed = Double.parseDouble(getText("ns3:Speed", extensionsTp));
                System.out.println("i am buying time");
            }
        }
    }

    private String getTextAttr (String attr,Element e) {
        return e.getAttribute(attr);
    }

    /*private String getText (String tag, Document d) {
        return "here?";
    }*/

    private String getText (String tag, Element e) {
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    private boolean attributeExists (String attr,Element e) {
        return (e.hasAttribute(attr));
    }

    private boolean tagExists (String tag, Document d) {
        return (d.getElementsByTagName(tag).getLength() != 0);
    }

    private boolean tagExists (String tag, Element e) {
        return (e.getElementsByTagName(tag).getLength() != 0);
    }
}
