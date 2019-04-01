function getProjects() {
  fetch('http://localhost:8100/projects')

    .then(response => response.json())
    .then(projects => displayProjects(projects));
}

function deleteProject(id) {
  if (confirm('Are you sure you want to delete a priority?')) {
    const deleteUrl = 'http://localhost:8100/project/' + id;
    fetch(deleteUrl, {
        method: 'DELETE'
      })
      .then(response => getProjects());
  }
}

function addOrEditProject() {
  if (validateProject()) {
    const project_id = document.getElementById('project_id').value;
    if (project_id > 0) {
      editProject();
    } else {
      addProject();
    }
  }
}

function validateProject() {
  const priority = document.getElementById('project_priority').value;
  const description = document.getElementById('project_descr').value;
  const date = document.getElementById('project_date').value;
  const time = document.getElementById('project_time').value;

  if (priority == null || priority.length < 1) {
    displayProjectValidationError("Missing!");
    return false;
  }

  if (description == null || description.length < 1) {
    displayProjectValidationError("Missing!");
    return false;
  }

    if (date == null || date.length < 1) {
      displayProjectValidationError("Missing!");
      return false;
    }

      if (time == null || time.length < 1) {
        displayProjectValidationError("Missing!");
        return false;
      }

  hideProjectValidationError();
  return true;
}

function addProject() {
  const priority = document.getElementById('project_priority').value;
  const description = document.getElementById('project_descr').value;
  const date = document.getElementById('project_date').value;
  const time = document.getElementById('project_time').value;

  const addUrl = 'http://localhost:8100/project';
  fetch(addUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        'project_priority': priority,
        'project_descr': description,
        'project_date': date,
        'project_time': time
      })
    })
    .then(response => {
      getProjects();
      closeProjectModal();
    });
}

function editProject() {
  const project_id = document.getElementById('project_id').value;
  const project_priority = document.getElementById('project_priority').value;
  const project_descr = document.getElementById('project_descr').value;
  const project_date = document.getElementById('project_date').value;
  const project_time = document.getElementById('project_time').value;

  const editUrl = 'http://localhost:8100/project';
  fetch(editUrl, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        'project_id': project_id,
        'project_priority': project_priority,
        'project_descr': project_descr,
        'project_date': project_date,
        'project_time': project_time
      })
    })
    .then(response => {
      getProjects();
      closeProjectModal();
    });
}

function displayProjects(projects) {
  let projectList = document.getElementById('projectList');
  projectList.innerHTML = "";

  for (let i = 0; i < projects.length; i++) {
    let projectRow = "";
    projectRow = "<tr>";
    // projectRow = projectRow + "<td>" + projects[i].project_id + "</td>";
    projectRow = projectRow + "<td>" + projects[i].project_priority + "</td>";
    projectRow = projectRow + "<td>" + projects[i].project_descr + "</td>";
    projectRow = projectRow + "<td>" + projects[i].project_date + "</td>";
    projectRow = projectRow + "<td>" + projects[i].project_time + "</td>";
    projectRow = projectRow + '<td>';
    projectRow = projectRow + '<div class="projectRowButtons"><button type="button" class="btn btn-danger" onClick="deleteProject(' + projects[i].project_id + ')">DELETE</button></div>' +
      '<div class="projectRowButtons"><button type="button" class="btn btn-dark" onClick="openProjectModal(' + projects[i].project_id + ')">EDIT</button></div>';
    projectRow = projectRow + "</td>";
    projectRow = projectRow + "</tr>";
    projectList.innerHTML += projectRow;
  }
}

function openProjectModal(id) {

  document.getElementById('project_id').value = null;
  document.getElementById('project_priority').value = null;
  document.getElementById('project_descr').value = null;
  document.getElementById('project_date').value = null;
  document.getElementById('project_time').value = null;
  $('#exampleModal').modal('show');

  if (id > 0) {
    const getProjectUrl = 'http://localhost:8100/project/' + id;
    fetch(getProjectUrl)
      .then(response => response.json())
      .then(project => {
        document.getElementById('project_id').value = project.project_id;
        document.getElementById('project_priority').value = project.project_priority;
        document.getElementById('project_descr').value = project.project_descr;
        document.getElementById('project_date').value = project.project_date;
        document.getElementById('project_time').value = project.project_time;
      });
  }
}

function closeProjectModal() {
  $('#exampleModal').modal('hide');
}

function displayProjectValidationError(errorText) {
  let errorDiv = document.getElementById('projectValidationError');
  errorDiv.style.display = 'block';
  errorDiv.innerHTML = errorText;
}

function hideProjectValidationError() {
  let errorDiv = document.getElementById('projectValidationError');
  errorDiv.style.display = 'none';
  errorDiv.innerHTML = '';
}





// FILTER TABLE SCRIPT.
$(document).ready(function filterTable(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#projectList tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
