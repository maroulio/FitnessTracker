package gr.hua.dit.fitnessmanager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TCXParser {
    private String tcx = "no tcx name yet";
    //parsing related variable
    Document doc;

    boolean exists;

    //maybe i do not need all these variables, they can get transferred directly in the setters

    //lap variables
    private LocalDateTime startTime;
    private double totalTimeSeconds;
    private double distanceMeters;
    private double maximumSpeed;
    private int calories;

    //running 2 does not have abpm and mbpm

    private int ABPM;
    private int MBPM;
    private String intensity;
    private String triggerMethod;
    private double avgSpeed;

    //cadence not in biking, swimming, running - only in walking
    private int avgRunCadence;
    private int maxRunCadence;

    //trackpoint variables
    private LocalDateTime time;
    private double latitudeDegrees;
    private double longitudeDegrees;
    private double altitudeMeters;
    private double distanceMetersTrackpoint;

    //running 2 does not have heart rate
    private int HRB;

    private double speed;



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

    public Activity parse () {
        //Node tcd = doc.getElementsByTagName("TrainingCenterDatabase");

        //boolean exists = tagExists("Activity", doc);
        NodeList activityList = doc.getElementsByTagName("Activity"); //keep this in case there is more than one activity in a file
        for (int i = 0; i < activityList.getLength(); i++) {


            Node a = activityList.item(i);
            if (a.getNodeType() == Node.ELEMENT_NODE) {
                Element act = (Element) a;
                //maybe I have to check if they are elements first before putting them in their category like string

                String sport = getTextAttr("Sport", act);
                ActivityFactory factory = new ActivityFactory();
                Activity activity = factory.createActivity(sport);


                //get id as a local date time not string

                //exists = tagExists("Id", act);
                String id = getText("Id", act);
                NodeList lapList = act.getElementsByTagName("Lap");

                //activity.setLaps(lapList);

                ArrayList<Laps> laps = parseLaps(lapList);
                activity.setLaps(laps);

                return activity;

            }
        }
    }

    private ArrayList<Laps> parseLaps (NodeList lapList) {
        ArrayList<Laps> laps = new ArrayList<>();
        for (int j = 0; j < lapList.getLength(); j++) {
            laps.add(j, new Laps());
            Node l = lapList.item(j);
            if (l.getNodeType() == Node.ELEMENT_NODE) {
                Element lap = (Element) l;

                String temp = getTextAttr("StartTime", lap);
                temp = temp.replace("Z", "");
                LocalDateTime startTime = LocalDateTime.parse(temp);
                laps.get(j).setStartTime() = startTime;

                totalTimeSeconds = Double.parseDouble(getText("TotalTimeSeconds", lap));
                laps.get(j).setTotalTimeSeconds() = totalTimeSeconds;
                distanceMeters = Double.parseDouble(getText("DistanceMeters", lap));
                laps.get(j).setDistanceMeters() = distanceMeters;
                maximumSpeed = Double.parseDouble(getText("MaximumSpeed", lap));
                laps.get(j).setMaximumSpeed() = maximumSpeed;
                calories = Integer.parseInt(getText("Calories", lap));
                laps.get(j).setCalories() = calories;


                //running 2 does not have abpm and mbpm
                Element el = (Element) lap.getElementsByTagName("AverageHeartRateBpm").item(0);
                if (tagExists("Value", el)) {
                    ABPM = Integer.parseInt(getText("Value", el));
                    laps.get(j).setAHR() = ABPM;
                }



                el = ((Element) lap.getElementsByTagName("MaximumHeartRateBpm").item(0));
                if (tagExists("Value", el)) {
                    MBPM = Integer.parseInt(getText("Value", el));
                    laps.get(j).setMHR() = MBPM;
                }

                /*MBPM = Integer.parseInt(((Element) lap.getElementsByTagName("MaximumHeartRateBpm")
                        .item(0)).getElementsByTagName("Value").item(0).getTextContent());*/

                //the class does not have intensity?????
                intensity = getText("Intensity", lap);
                //laps.get(j).

                //the class does not have trigger method?????
                triggerMethod = getText("TriggerMethod", lap);
                //laps.get(j).

                Element extensions = (Element) ((Element) lap.getElementsByTagName("Extensions")
                        .item(lap.getElementsByTagName("Extensions").getLength() - 1)).getElementsByTagName("ns3:LX").item(0);

                avgSpeed = Double.parseDouble(getText("ns3:AvgSpeed", extensions));
                laps.get(j).setAverageSpeed() = avgSpeed;


                //run cadence in laps???????????????????????????????????

                //cadence not in biking, swimming, running - only in walking
                if (tagExists("ns3:AvgRunCadence", extensions)) {
                    avgRunCadence = Integer.parseInt(getText("ns3:AvgRunCadence", extensions));
                    //laps.get(j)
                }

                if (tagExists("ns3:MaxRunCadence", extensions)) {
                    maxRunCadence = Integer.parseInt(getText("ns3:MaxRunCadence", extensions));
                   // laps.get(j)
                }

                NodeList trackList = lap.getElementsByTagName("Track");
                ArrayList<Tracks> tracks = parseTracks(trackList);
                laps.get(j).setTracksList(tracks);
            }
        }
        return laps;
    }

    private ArrayList<Tracks> parseTracks (NodeList trackList) {
        ArrayList<Tracks> tracks = new ArrayList<>();
        for (int z = 0; z < trackList.getLength(); z++) {
            tracks.add(z, new Tracks());
            Node t = trackList.item(z);
            if (t.getNodeType() == Node.ELEMENT_NODE) {
                Element track = (Element) t;

                NodeList trackpointList = track.getElementsByTagName("Trackpoint");
                ArrayList<Trackpoints> trackpoints = parseTrackpoints(trackpointList);
                tracks.get(z).setTrackpoints(trackpoints);
            }
        }
        return tracks;
    }

    private ArrayList<Trackpoints> parseTrackpoints (NodeList trackpointList) {
        ArrayList<Trackpoints> trackpoints = new ArrayList<>();
        for (int k = 0; k < trackpointList.getLength(); k++) {
            trackpoints.add(k, new Trackpoints());
            Node trckpt = trackpointList.item(k);
            if (trckpt.getNodeType() == Node.ELEMENT_NODE) {
                Element tp = (Element) trckpt;

                String temp = getText("Time", tp);
                temp = temp.replace("Z", "");
                time = LocalDateTime.parse(temp);
                trackpoints.get(k).setTimeStamp(time);

                Element position = (Element) tp.getElementsByTagName("Position").item(0);

                latitudeDegrees = Double.parseDouble(getText("LatitudeDegrees", position));
                trackpoints.get(k).setLatitudeDegrees(latitudeDegrees);
                longitudeDegrees = Double.parseDouble(getText("LongitudeDegrees", position));
                trackpoints.get(k).setLongitudeDegrees(longitudeDegrees);
                altitudeMeters = Double.parseDouble(getText("AltitudeMeters", tp));
                trackpoints.get(k).setAltitudeMeters(altitudeMeters);
                distanceMetersTrackpoint = Double.parseDouble(getText("DistanceMeters", tp));
                trackpoints.get(k).setDistanceMeters(distanceMetersTrackpoint);

                System.out.println(distanceMetersTrackpoint);

                //running 2 does not have heart rate
                Element el = (Element) tp.getElementsByTagName("HeartRateBpm").item(0);
                if (tagExists("Value", el)) {
                    HRB = Integer.parseInt(getText("Value", el));
                    trackpoints.get(k).setHeartRate(HRB);
                }


                Element extensionsTp = (Element)((Element) tp.getElementsByTagName("Extensions").item(0))
                        .getElementsByTagName("ns3:TPX").item(0);


                speed = Double.parseDouble(getText("ns3:Speed", extensionsTp));
                trackpoints.get(k).setSpeed(speed);
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
