const form = document.querySelector('#form');
const taskList = document.querySelector('#task-list');
let tasks = [];

form.addEventListener('submit', e => {
  e.preventDefault();
 
  const task = Object.create(null);
  task.description = e.target.description.value.trim();
  task.done = false;

  form.reset();

  addTask(task);
})

const addTask = async task => {
  const url = 'http://localhost:8080/api/v1/tasks';
  const options = {
    headers: { 'Content-Type': 'application/json' },
    method: 'POST',
    body: JSON.stringify(task)
  }

  const response = await fetch (url, options);
  const data = await response.json();

  tasks.push(data);

  getTasks(tasks);
}

const getTasks = async() => {
  const url = 'http://localhost:8080/api/v1/tasks/tasks';
  const options = {
    headers: {'Accept': 'application/json'},
    method: 'GET'
  }

  const response = await fetch(url, options);
  const tasks = await response.json();

  renderTasks(tasks);
}

getTasks();

const updateTask = async (taskId, taskToUpdate ) => {
  const url = `http://localhost:8080/api/v1/tasks/${taskId}`;
  const options = {
    headers: {'Accept': 'application/json'},
    method: 'PUT',
    body: JSON.stringify(taskToUpdate)
  }

  await fetch(url, options);

  getTasks();
}

const deleteTask = async taskId => {
  const url = `http://localhost:8080/api/v1/tasks/${taskId}`;

  await fetch (url, { method: 'DELETE' })

  tasks = tasks.filter(task => task.id !== taskId);

  getTasks(tasks);
}

const renderTasks = tasks => {

  taskList.innerHTML = '';

  tasks.map(task => {
    const taskItem = document.createElement('div');
    taskItem.classList.add('task-item');

    const description = document.createElement('p');
    description.textContent = task.description;
    description.classList.add('description');
    if (task.done) {
      description.style.textDecoration = "line-through";
    }

    const check = document.createElement('span');
    check.textContent = 'check_circle';
    check.classList.add('material-symbols-outlined', 'green-check');
    check.setAttribute('data-id', task.id);
    check.addEventListener('click', e => {
      const taskId =  parseInt(e.currentTarget.getAttribute('data-id')); 
      const taskToUpdate = tasks.filter(task => task.id === taskId) [0];
      taskToUpdate.done = true;

      updateTask(taskId, taskToUpdate);
    }) 

    const remove = document.createElement('span');
    remove.textContent = 'delete';
    remove.classList.add('material-symbols-outlined', 'red-remove');
    remove.setAttribute('data-id', task.id);
    remove.addEventListener('click', e => {
      const taskId = e.currentTarget.getAttribute('data-id');;
      deleteTask(taskId);
    })

    taskItem.append(description, check, remove)
    taskList.append(taskItem);
  })

}