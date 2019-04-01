package company.priorify.Entity;

public class EntityProject {

    // PRIMARY values.
    private int project_id;
    private String project_priority;
    private String project_descr;
    private String project_date;
    private String project_time;

    // CONSTRUCTOR.
    public EntityProject(int project_id, String project_priority, String project_descr, String project_date, String project_time) {
        this.project_id = project_id;
        this.project_priority = project_priority;
        this.project_descr = project_descr;
        this.project_date = project_date;
        this.project_time = project_time;
    }

    // GETTERS and SETTERS.
    public EntityProject() {
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_priority() {
        return project_priority;
    }

    public void setProject_priority(String project_priority) {
        this.project_priority = project_priority;
    }

    public String getProject_descr() {
        return project_descr;
    }

    public void setProject_descr(String project_descr) {
        this.project_descr = project_descr;
    }

    public String getProject_date() {
        return project_date;
    }

    public void setProject_date(String project_date) {
        this.project_date = project_date;
    }

    public String getProject_time() {
        return project_time;
    }

    public void setProject_time(String project_time) {
        this.project_time = project_time;
    }
}
