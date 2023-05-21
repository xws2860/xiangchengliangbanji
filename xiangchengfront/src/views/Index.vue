<script>
import SceneView from '../components/SceneView.vue'
import InformationTab from '../components/InformationTab.vue'
import { ref } from 'vue'
import { ElButton } from 'element-plus'

export default {
  name: 'Index',
  components: {
    SceneView,
    ElButton,
    InformationTab
  },
  data() {
    return {
      mapProperties: {
        portalItem: {
          // 在此处设置您的 Portal Item ID 或 URL
        }
      },
      viewProperties: {
        // 在此处设置 SceneView 的属性，例如初始位置、缩放级别等
        center: [120, 30],
        zoom: 4
      }
    }
  },
  methods: {
    onMapReady(sceneView) {
      // 场景视图已准备就绪
      console.log('场景视图已加载')
    },
    hadleSetFid(fid) {
      this.fid = fid.FID
      console.log('这里是', fid)
      const informationTab = this.$refs.informationTab
      informationTab.openDialog()
    },
    hadleShorelinePlanningClick(fid) {
      console.log('岸线规划')
      console.log(fid)
    }
  },
  setup() {
    const fid = ref(0) // 假设当前河流信息的fid为123
    return {
      fid
    }
  }
}
</script>
<template>
  <SceneView :mapProperties="mapProperties" @map-ready="onMapReady" @setFid="hadleSetFid" @shorelinePlanningClick="hadleShorelinePlanningClick" />
  <information-tab :fid="fid" ref="informationTab"></information-tab>
</template>
<style scoped>
</style>
