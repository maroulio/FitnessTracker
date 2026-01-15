package gr.hua.dit.fitnessmanager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TCXParser {
    Document doc;

    public TCXParser(File tcx) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(tcx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Activity> parse () {
        ArrayList<Activity> activities = new ArrayList<>();
        NodeList activityList = doc.getElementsByTagName("Activity");
        for (int i = 0; i < activityList.getLength(); i++) {

            Node a = activityList.item(i);
            if (a.getNodeType() == Node.ELEMENT_NODE) {
                Element act = (Element) a;

                String sport = getTextAttr("Sport", act);
                ActivityFactory factory = new ActivityFactory();
                Activity activity = factory.createActivity(sport);
                activities.add(activity);
                activity.setSport(sport);

                //get id as a local date time not string maybe
                //String id = getText("Id", act);
                NodeList lapList = act.getElementsByTagName("Lap");

                ArrayList<Laps> laps = parseLaps(lapList);
                activity.setLaps(laps);

            }
        }
        return activities;
    }

    private ArrayList<Laps> parseLaps (NodeList lapList) {
        ArrayList<Laps> laps = new ArrayList<>();
        for (int i = 0; i < lapList.getLength(); i++) {
            Laps lapObj = new Laps();
            laps.add(lapObj);
            Node l = lapList.item(i);
            if (l.getNodeType() == Node.ELEMENT_NODE) {
                Element lap = (Element) l;

                String temp = getTextAttr("StartTime", lap);
                temp = temp.replace("Z", "");
                LocalDateTime startTime = LocalDateTime.parse(temp);
                lapObj.setStartTime(startTime);

                double totalTimeSeconds = Double.parseDouble(getText("TotalTimeSeconds", lap));
                lapObj.settotalTimeSeconds(totalTimeSeconds);
                double distanceMeters = Double.parseDouble(getText("DistanceMeters", lap));
                lapObj.setDistanceMeters(distanceMeters);

                //double maximumSpeed = Double.parseDouble(getText("MaximumSpeed", lap));
                //lapObj.set.setMaximumSpeed(maximumSpeed);
                int calories = Integer.parseInt(getText("Calories", lap));
                lapObj.setCalories(calories);


                //running 2 does not have abpm and mbpm
                Element el = (Element) lap.getElementsByTagName("AverageHeartRateBpm").item(0);
                if (tagExists("Value", el)) {
                    int ABPM = Integer.parseInt(getText("Value", el));
                    lapObj.setAvgHR(ABPM);
                }



                el = ((Element) lap.getElementsByTagName("MaximumHeartRateBpm").item(0));
                if (tagExists("Value", el)) {
                    int MBPM = Integer.parseInt(getText("Value", el));
                    lapObj.setMaxHR(MBPM);
                }

                //the class does not have intensity?????
                //String intensity = getText("Intensity", lap);
                //laps.get(i).

                //the class does not have trigger method?????
                //String triggerMethod = getText("TriggerMethod", lap);
                //laps.get(i).

                Element extensions = (Element) ((Element) lap.getElementsByTagName("Extensions")
                        .item(lap.getElementsByTagName("Extensions").getLength() - 1)).getElementsByTagName("ns3:LX").item(0);

                double avgSpeed = Double.parseDouble(getText("ns3:AvgSpeed", extensions));
                lapObj.setAvgSpeed(avgSpeed);


                //run cadence in laps???????????????????????????????????

                //cadence not in biking, swimming, running - only in walking
                /*if (tagExists("ns3:AvgRunCadence", extensions)) {
                    //cadence not in biking, swimming, running - only in walking
                    int avgRunCadence = Integer.parseInt(getText("ns3:AvgRunCadence", extensions));
                    //laps.get(i)
                }

                if (tagExists("ns3:MaxRunCadence", extensions)) {
                    int maxRunCadence = Integer.parseInt(getText("ns3:MaxRunCadence", extensions));
                   // laps.get(i)
                }*/

                NodeList trackList = lap.getElementsByTagName("Track");
                ArrayList<Tracks> tracks = parseTracks(trackList);
                lapObj.setTracks(tracks);
            }
        }
        return laps;
    }

    private ArrayList<Tracks> parseTracks (NodeList trackList) {
        ArrayList<Tracks> tracks = new ArrayList<>();
        for (int i = 0; i < trackList.getLength(); i++) {
            Tracks trackObj = new Tracks();
            tracks.add(trackObj);
            Node t = trackList.item(i);
            if (t.getNodeType() == Node.ELEMENT_NODE) {
                Element track = (Element) t;

                NodeList trackpointList = track.getElementsByTagName("Trackpoint");
                ArrayList<Trackpoints> trackpoints = parseTrackpoints(trackpointList);
                trackObj.setTrackpoints(trackpoints);
            }
        }
        return tracks;
    }

    private ArrayList<Trackpoints> parseTrackpoints (NodeList trackpointList) {
        ArrayList<Trackpoints> trackpoints = new ArrayList<>();
        for (int i = 0; i < trackpointList.getLength(); i++) {
            Trackpoints trackptObj = new Trackpoints();
            trackpoints.add(trackptObj);
            Node trckpt = trackpointList.item(i);
            if (trckpt.getNodeType() == Node.ELEMENT_NODE) {
                Element tp = (Element) trckpt;

                String temp = getText("Time", tp);
                temp = temp.replace("Z", "");
                LocalDateTime time = LocalDateTime.parse(temp);
                trackptObj.setTimeStamp(time);

                Element position = (Element) tp.getElementsByTagName("Position").item(0);

                double latitudeDegrees = Double.parseDouble(getText("LatitudeDegrees", position));
                trackptObj.setLatitudeDegrees(latitudeDegrees);
                double longitudeDegrees = Double.parseDouble(getText("LongitudeDegrees", position));
                trackptObj.setLongitudeDegrees(longitudeDegrees);
                double altitudeMeters = Double.parseDouble(getText("AltitudeMeters", tp));
                trackptObj.setAltitudeMeters(altitudeMeters);
                double distanceMetersTrackpoint = Double.parseDouble(getText("DistanceMeters", tp));
                trackptObj.setDistanceMeters(distanceMetersTrackpoint);

                System.out.println(distanceMetersTrackpoint);

                Element el = (Element) tp.getElementsByTagName("HeartRateBpm").item(0);
                if (tagExists("Value", el)) {
                    //running 2 does not have heart rate
                    int HRB = Integer.parseInt(getText("Value", el));
                    trackptObj.setHeartRate(HRB);
                }


                Element extensionsTp = (Element)((Element) tp.getElementsByTagName("Extensions").item(0))
                        .getElementsByTagName("ns3:TPX").item(0);


                double speed = Double.parseDouble(getText("ns3:Speed", extensionsTp));
                trackptObj.setSpeed(speed);
                System.out.println("i am buying time");
            }
        }
        return trackpoints;
    }

    private String getTextAttr (String attr,Element e) {
        return e.getAttribute(attr);
    }

    private String getText (String tag, Element e) {
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    /*private boolean attributeExists (String attr,Element e) {
        return (e.hasAttribute(attr));
    }*/

    /*private boolean tagExists (String tag, Document d) {
        return (d.getElementsByTagName(tag).getLength() != 0);
    }*/

    private boolean tagExists (String tag, Element e) {
        return (e.getElementsByTagName(tag).getLength() != 0);
    }
}