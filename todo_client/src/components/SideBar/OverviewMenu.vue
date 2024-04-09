// menu to select overview today or week
<template>
  <div id="overview-menu">
    <router-link to='/today' active-class="active"><today-icon class="icon"/><span>Сегодня</span></router-link>
    <router-link to='/week' active-class="active"><week-icon class="icon"/><span>На неделе</span></router-link>
  </div>
</template>

<script>
import todayIcon from '../icons/todayIcon.vue'
import WeekIcon from '../icons/weekIcon.vue'
export default {
  components: { todayIcon, WeekIcon },
  
}
</script>

<style scoped>
a {
  display: block;
  padding: 5px;
  margin: 5px;
  border-radius: 5px;
  text-align: left;
}

a span {
  margin-left: 5px;
  position: relative;
  top: -5px;
}

a:hover {
  background-color: #f6eeee;
}

.active {
  background-color: #fee6e3;
}
.active:hover {
  background-color: #fee6e3;
}

.icon {
  width: 28px;
}
</style>