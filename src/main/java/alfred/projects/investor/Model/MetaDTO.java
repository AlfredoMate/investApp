package alfred.projects.investor.Model;

public class MetaDTO {

    private String date_from;
    private String date_to;
    private Integer max_period_days;

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public Integer getMax_period_days() {
        return max_period_days;
    }

    public void setMax_period_days(Integer max_period_days) {
        this.max_period_days = max_period_days;
    }
}
