package zzzde.n.knowledge.pattern.templatePattern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/4/1 15:34
 */
public class Mast {

    public static void main(String[] args) {
        //1.save to db

        new Template().template(1,
                projectId->getMastsfromMongo(projectId),
                masts-> masts.stream().map(mast->mast.getName()).collect(Collectors.toList()),
                names->System.out.println("save names to db "+ names));
        //new Template(1, id->Arrays, );

        //2.print to console


        new Template().template(2,
                projectId->getMastsSomewhere(projectId),
                masts-> masts.stream().map(mast->mast.getLat()).collect(Collectors.toList()),
                names->System.out.println("print lons to console "+ names));
    }



    private static List<Mast> getMastsfromMongo(int projectId){

        Mast m1 = new Mast("1", 110, 23);
        Mast m2 = new Mast("2", 111, 13);

        return Arrays.asList(m1, m2);
    }

    private static List<Mast> getMastsSomewhere(int projectId){

        Mast m1 = new Mast("3", 120, 53);
        Mast m2 = new Mast("4", 121, 54);

        return Arrays.asList(m1, m2);
    }





    private String name;
    private double lon;
    private double lat;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }

    public Mast(String name, double lon, double lat) {
        super();
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }


}
