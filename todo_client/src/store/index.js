import axios from 'axios';
import { createStore } from 'vuex'
import router from '@/router';

var url = 'http://' + process.env.VUE_APP_HOST + ':80';

// function formatDate(date) {
//   let year = date.getFullYear();
//   let month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
//   let day= date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
//   let hour = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
//   let minute = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
//   return year + '-' + month + '-' + day + 'T' + hour + ':' + minute;
// }

function buildTaskForm(task) {
  return {
    title: task.title,
    description: task.description,
    expireAt: task.expireAt,
    priority: task.priority,
    isDone: task.isDone,
    projectId: task.project.id
  }
}

export default createStore({
  state: {
    user: {}, // User from backend
    auth: {}, // username and password used for basic auth provided by user
    projects: [], // all projects for authed user
    tasks: [], // all tasks for authed user
  },
  getters: {
    projectById: (state) => (projectId) => {
      return state.projects.find(item => item.id === projectId);
    },
    taskById: (state) => (taskId) => {
      return state.tasks.find(item => item.id === taskId);
    },
    filteredTasks: (state) => (filterCallback) => {
      return state.tasks.filter(filterCallback);
    }
  },
  mutations: {
    setUser(state, payload) {
      state.user = payload.user;
    },
    setAuth(state, payload) {
      state.auth = payload.auth;
    },

    setProjects(state, payload) {
      payload.projects.map((project) => {
        if (project.expireAt != null) {
          project.expireAt = new Date(project.expireAt);
        }
      });
      state.projects = payload.projects;
    },
    setTasks(state, payload) {
      payload.tasks.map((task) => {
        if (task.expireAt != null) {
          task.expireAt = new Date(task.expireAt);
        }
      });
      state.tasks = payload.tasks;
    },
    // change task's done state. Arg = payload.isDone
    setTaskDone(state, payload) {
      let task = state.tasks.find(item => item.id === payload.taskId);
      task.isDone = payload.isDone;
    },
    addTask(state, payload) {
      if (payload.task.expireAt != null) {
        payload.task.expireAt = new Date(payload.task.expireAt);
      }
      state.tasks.push(payload.task);
    },
    deleteTask(state, payload) {
      let taskId = payload.taskId;
      let taskIndex = state.tasks.findIndex(item => item.id === taskId);
      state.tasks.splice(taskIndex, 1);
    },
    addProject(state, payload) {
      let project = payload.project;
      if (project.expireAt != null) {
        project.expireAt = new Date(project.expireAt);
      }
      state.projects.push(project);
    },
    deleteProject(state, payload) {
      let projectId = payload.projectId;
      let projectIndex = state.projects.findIndex(item => item.id === projectId);
      state.projects.splice(projectIndex, 1);

    },
  },
  actions: {
    signIn(context, payload)  {
      axios.post(url + '/api/user/signin', payload.userForm)
      .then(data => {
        if (data.status === 200) {
          context.commit('setUser', {user: data.data});
          context.commit('setAuth', {auth: payload.userForm});
          context.dispatch('fetchProjects');
          context.dispatch('fetchTasks');
          router.push('/today');
        } else {
          alert('Error');
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },

    signUp(context, payload) {
      axios.post(url + '/api/user/signup', payload.userForm)
      .then(data => {
        if (data.status === 200) {
          router.push('/today');
        }
      })
    },

    // Get all project for current user
    fetchProjects(context) {
      axios.get(url + '/api/project', {auth: this.state.auth})
      .then(data => {
        context.commit('setProjects', {projects: data.data})
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },
    // Get all tasks for current user
    fetchTasks(context) {
      axios.get(url + '/api/task', {auth:this.state.auth})
      .then(data => {
        context.commit('setTasks', {tasks: data.data});
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },
    toggleTaskDone(context, payload) {
      let taskId = payload.taskId;
      let task = context.getters.taskById(taskId);

      let taskForm = buildTaskForm(task);
      taskForm.isDone = !taskForm.isDone;

      axios.put(url + '/api/task/' + taskId, taskForm, {auth: this.state.auth})
      .then(data => {
        
        if (data.status === 200) {
          this.commit('setTaskDone', {taskId: taskId, isDone: taskForm.isDone});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },

    createTask(context, payload) {
      let taskForm = payload.formData;
      axios.post(url + '/api/task', taskForm, {auth: this.state.auth})
      .then(data => {
        if (data.status === 200) {
          context.commit("addTask", {task: data.data});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },

    changeTask(context, payload) {
      let taskForm = payload.formData;
      let taskId = payload.taskId;
      axios.put(url + '/api/task/' + taskId, taskForm, {auth: context.state.auth})
      .then(data => {
        if (data.status === 200) {
          context.commit('deleteTask', {taskId: data.data.id});
          context.commit('addTask', {task: data.data});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },

    deleteTask(context, payload) {
      let taskId = payload.taskId;
      axios.delete(url + '/api/task/' + taskId, {auth:context.state.auth})
      .then(data => {
        if (data.status === 200) {
          context.commit("deleteTask", {taskId: taskId});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },

    createProject(context, payload) {
      let projectForm = payload.formData;
      axios.post(url + '/api/project', projectForm, {auth: this.state.auth})
      .then(data => {
        if (data.status === 200) {
          context.commit('addProject', {project: data.data});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },

    changeProject(context, payload) {
      let projectId = payload.projectId;
      let projectForm = payload.formData;
      axios.put(url + '/api/project/' + projectId, projectForm, {auth: this.state.auth})
      .then(data => {
        if (data.status === 200) {
          context.commit('deleteProject', {projectId: projectId});
          context.commit('addProject', {project: data.data});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },
    deleteProject(context, payload) {
      let projectId = payload.projectId;

      axios.delete(url + '/api/project/' + projectId, {auth: this.state.auth})
      .then(data => {
        if (data.status === 200) {
          context.commit('deleteProject', {projectId: projectId});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },
    changeUserEmail(context, payload) {
      axios.patch(url + '/api/user/changeemail', {newEmail: payload.newEmail}, {auth: this.state.auth})
      .then(data => {
        if (data.status === 200) {
          context.commit('setUser', {user: data.data});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },

    changeUserPassword(context, payload) {
      axios.patch(url + '/api/user/changepassword', payload.passwordForm, {auth: this.state.auth})
      .then(data => {
        if (data.status === 200) {
          context.commit('setUser', {user: data.data});
          context.commit('setAuth', {auth: {username: data.data.username, password: payload.passwordForm.newPassword}});
        }
      }).catch(error => {
        console.log(error);
        alert("error");
      });
    },
  },
  modules: {
  }
})
