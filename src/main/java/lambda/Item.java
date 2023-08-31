package lambda;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {
//    public static final long versionId = 1l;
    private Integer id;
    private String s;
    private double v;
    private long l;
    private Date createDate;
    private String statusCd;

    public Item(String statusCd) {
        this.statusCd = statusCd;
    }

    public Item(Date createDate) {
        this.createDate = createDate;
    }

    public Item(long l) {
        this.l = l;
    }
    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public Item(Integer id, String s, double v, long l, Date createDate) {
        this.id = id;
        this.s = s;
        this.v = v;
        this.l = l;
        this.createDate = createDate;
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Item() {
    }

    public Item(int id, String s, double v) {
        super();
        this.id = id;
        this.s = s;
        this.v = v;
    }

    public Item(Integer id, String s) {
        this.id = id;
        this.s = s;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", s='" + s + '\'' +
                ", v=" + v +
                ", l=" + l +
                ", createDate=" + createDate +
                '}';
    }

    public static int compareById(Item s1, Item s2) {
        return s1.id.compareTo(s2.id);
    }



}
