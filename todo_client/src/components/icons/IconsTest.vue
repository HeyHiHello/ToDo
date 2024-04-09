<script>
import AddIcon from "@/components/icons/addIcon.vue";
import DoneIcon from "@/components/icons/doneIcon.vue";
import HashTagIcon from "@/components/icons/hashTagIcon.vue";
import OptionsIcon from "@/components/icons/optionsIcon.vue";
import PencilIcon from "@/components/icons/pencilIcon.vue";
import TodayIcon from "@/components/icons/todayIcon.vue";
import TrashIcon from "@/components/icons/trashIcon.vue";
import UserIcon from "@/components/icons/userIcon.vue";
import WeekIcon from "@/components/icons/weekIcon.vue";
import CollapserIcon from "@/components/icons/collapserIcon.vue";

export default {
  name: "IconsTest",
  components: {
    CollapserIcon,
    WeekIcon, UserIcon, TrashIcon, TodayIcon, PencilIcon, OptionsIcon, HashTagIcon, DoneIcon, AddIcon
  },
  methods: {
    click() {
      this.$refs.Collapser.toggle();
    }
  }
}
</script>

<template>
  <add-icon></add-icon>
  <collapser-icon closed-angle="180" ref="Collapser" v-on:click="click()"></collapser-icon>
  <done-icon></done-icon>
  <hash-tag-icon></hash-tag-icon>
  <options-icon></options-icon>
  <pencil-icon></pencil-icon>
  <today-icon></today-icon>
  <trash-icon></trash-icon>
  <user-icon></user-icon>
  <week-icon></week-icon>
</template>

<style scoped>

</style>