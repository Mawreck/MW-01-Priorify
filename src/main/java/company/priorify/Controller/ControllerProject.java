package company.priorify.Controller;


import company.priorify.Entity.EntityProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerProject {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // LIST all the PROJECTS.
    @GetMapping("/projects")
    public List<EntityProject> getProjects() {
        List<EntityProject> projects = jdbcTemplate.query(
                "SELECT * FROM projects", (row, count) -> {
                    int project_id = row.getInt("project_id");
                    String project_priority = row.getString("project_priority");
                    String project_descr = row.getString("project_descr");
                    String project_date = row.getString("project_date");
                    String project_time = row.getString("project_time");

                    EntityProject project = new EntityProject();
                    project.setProject_id(project_id);
                    project.setProject_priority(project_priority);
                    project.setProject_descr(project_descr);
                    project.setProject_date(project_date);
                    project.setProject_time(project_time);

                    return project;
                }
        );
        return projects;
    }

    // GET one PROJECT.
    @GetMapping("/project/{id}")
    public EntityProject getProject(@PathVariable int id) {
        EntityProject result = jdbcTemplate.queryForObject(
                "SELECT * FROM projects WHERE project_id = ?",
                new Object[]{id}, (row, count) -> {
                    int project_id = row.getInt("project_id");
                    String project_priority = row.getString("project_priority");
                    String project_descr = row.getString("project_descr");
                    String project_date = row.getString("project_date");
                    String project_time = row.getString("project_time");

                    EntityProject project = new EntityProject();
                    project.setProject_id(project_id);
                    project.setProject_priority(project_priority);
                    project.setProject_descr(project_descr);
                    project.setProject_date(project_date);
                    project.setProject_time(project_time);

                    return project;
                });
        return result;
    }

    // DELETE a PROJECT.
    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable int id) {

        jdbcTemplate.update("DELETE FROM projects WHERE project_id = ?", id);
    }

    // ADD a PROJECT.
    @PostMapping("/project")
    public void addProject(@RequestBody EntityProject project) {
        jdbcTemplate.update("INSERT INTO projects (project_priority, project_descr, project_date, project_time) values (?, ?, ?, ?)",
                project.getProject_priority(), project.getProject_descr(), project.getProject_date(), project.getProject_time());
    }

    // ADD PROJECTS.
    @PostMapping("/projects")
    public void addProjects(@RequestBody EntityProject[] projects) {
        for (EntityProject project : projects) {
            addProject(project);
        }
    }

    // EDIT PROJECT.
    @PutMapping("/project")
    public void editProject(@RequestBody EntityProject project) {
        jdbcTemplate.update(
                "UPDATE projects SET project_priority = ?, project_descr = ?, project_date = ?, project_time = ? WHERE project_id = ?",
                project.getProject_priority(), project.getProject_descr(), project.getProject_date(), project.getProject_time(), project.getProject_id());
    }
}
