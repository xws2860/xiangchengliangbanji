<template>
  <div class="navbar">
    <div class="navbar-left">
      <img src="/images/logo.png" alt="Avatar">
      <h1>乡城县水利数字沙盘</h1>
    </div>
    <div class="navbar-right">
      <img src="/images/avatar.jpg" alt="Avatar">
      <p>格桑梅朵</p>
    </div>
  </div>
  <div ref="mapViewNode" style="height: calc(100vh - 40px);">
    <div ref="basemapGalleryNode"></div>
    <div class="measure-tools-wrapper">
      <div class="measure-tools">
        <div class="measure-tools-icon" @click="toggleDirectLine()"><el-icon color="rgb(110,110,110)" :size="20">
            <EditPen />
          </el-icon></div>
        <div class="measure-tools-icon" @click="toggleArea()"><el-icon color="rgb(110,110,110)" :size="20">
            <Edit />
          </el-icon></div>
      </div>
    </div>
    <div class="map-FigureLayer">
      <div class="map-FigureLayer-Font" @click="showFigureLayer()">
        <p>图层</p>
      </div>
      <div class="map-FigureLayer-TF" v-if="FigureLayerVisible">
        <div v-for="(i,index) in FigureFigureLayerFont" :key="index" class="map-FigureLayer-TFdiv">
          <p v-text="i"></p>
          <el-switch v-model="FigureLayerInsideVisible[index]" class="mt-2" style="margin-left: 24px" inline-prompt :active-icon="Check" :inactive-icon="Close" />
        </div>
      </div>
    </div>

  </div>
</template>
<script>
import SceneView from '@arcgis/core/views/SceneView'
import WebScene from '@arcgis/core/WebScene'
import esriConfig from '@arcgis/core/config'
import Legend from '@arcgis/core/widgets/Legend'
import MapImageLayer from '@arcgis/core/layers/MapImageLayer'
import DirectLineMeasurement3D from '@arcgis/core/widgets/DirectLineMeasurement3D'
import AreaMeasurement3D from '@arcgis/core/widgets/AreaMeasurement3D'
import BasemapGallery from '@arcgis/core/widgets/BasemapGallery'
import { ElNotification } from 'element-plus'
import { h } from 'vue'
import { Check, Close } from '@element-plus/icons-vue'

export default {
  name: 'SceneView',
  data() {
    return {
      sceneView: null,
      directLineVisible: false,
      areaVisible: false,   //一开始的面积测量和距离测量是关闭状态???
      FigureLayerVisible:false,  //一开始的TF选项是隐藏的，只有点击后才是可见的
      FigureFigureLayerFont:['水系','定曲岸线规划','许曲岸线规划','水电站','水文站','县（区)界','乡（镇）界','晕线1','晕线2','乡镇面 (10)','乡城县农田灌溉面积(水资源)'],
      FigureLayerInsideVisible:[true,true,true,true,true,true,true,true,true,true,true],
      Check,
      Close
    }
  },
  created() {
    document.body.style.marginTop = '40px'
  },
  mounted() {
    this.initializeMap()
  },
  methods: {
    async initializeMap() {
      try {
        esriConfig.apiKey = 'AAPKfcfab4769ecd4082a0983c91ddb91a10qY1wTLqICXuld4YQGysCEGlH46-8nmNBS517S_kHqDUwYvk9P02AdG8B_gtG2UcR'
        let map = new WebScene({
          portalItem: {
            id: '5a392557cffb485f8fe004e668e9edc0'
          }
        })
        // Create the SceneView
        let view = new SceneView({
          // map：指定需要渲染的 Web 地图或场景实例。
          map: map,
          // container：指定地图渲染的容器，此处传入 'app' 表示渲染到 id 为 'app' 的 HTML 元素上。
          container: 'app',
          // center：指定地图视角的中心点坐标， [99.8, 29.1] 表示经度为 99.8，纬度为 29.1。
          center: [99.8, 29.1],
          // zoom：指定地图的缩放级别,表示缩放级别为 10。
          zoom: 10,
          // environment：指定地图的环境设置，{ lighting: null } 表示使用默认的光照环境。
          environment: {
            lighting: null
          },
          // popup：指定弹出窗口的设置
          // 表示禁用弹出窗口的操作按钮，并在地图右侧显示弹出窗口。
          popup: {
            actions: [],
            dockEnabled: true,
            dockOptions: {
              buttonEnabled: true,
              breakpoint: false
            }
          }
        })
        //添加图例
        let legend = new Legend({
          view: view, // 地图视图对象
          container: 'legendDiv', // 指定容器元素的 ID
          style: {
            backgroundColor: 'white',
            borderColor: 'black',
            fontFamily: 'Arial, sans-serif',
            fontSize: '14px',
            fontWeight: 'normal',
            opacity: 0.8
          }
        })
        // 将图例组件添加到地图中
        view.ui.add(legend, 'bottom-right')
        //加载map service
        let apiUrl = import.meta.env.VITE_MAP_SERVER_URL
        // console.log(apiUrl);
        console.log(this)
        let layer = new MapImageLayer({
          // 指定地图服务的 URL 地址
          url: apiUrl,
          // outFields: 指定需要查询的字段信息
          // ['*'] 表示查询所有字段。
          outFields: ['*'],
          // sublayers：指定地图中的子图层信息，包括 ID、可见性、弹出窗口模板等属性。
          sublayers: [
            {
              // 水系
              id: 0,
              visible: this.FigureLayerInsideVisible[0],
              autoCloseEnabled: true,
              // popupTemplate 属性指定了子图层的弹出窗口模板，用于在用户点击该图层时显示相关信息。
              popupTemplate: {
                content: e => {
                  console.log(e)
                  this.$emit('setFid', e.graphic.attributes)
                }
              }
            },
            {
              // 定曲岸线规划
              id: 1,
              visible: this.FigureLayerInsideVisible[1],
              autoCloseEnabled: true,
              popupTemplate: {
                content: e => {
                  // e.graphic.attributes 表示当前弹出窗口关联的要素（feature）的属性信息
                  // e 表示事件对象，graphic 表示与事件相关的图形对象，attributes 则表示该图形对象所关联的要素的属性信息
                  // 通过访问 attributes 属性，可以获取到该要素的所有属性信息
                  console.log(attributes)
                  this.$emit('shorelinePlanningClick', e.graphic.attributes)
                }
              }
            },
            {
              // 许曲岸线规划
              id: 2,
              visible: this.FigureLayerInsideVisible[2],
              autoCloseEnabled: true,
              popupTemplate: {
                content: e => {
                  console.log(e)
                  this.$emit('shorelinePlanningClick', e.graphic.attributes)
                }
              }
            },
            {
              // 水电站
              id: 3,
              visible: this.FigureLayerInsideVisible[3]
            },
            {
              id: 4,
              visible: this.FigureLayerInsideVisible[4]
            },
            {
              id: 5,
              visible: this.FigureLayerInsideVisible[5]
            },
            {
              id: 6,
              visible: this.FigureLayerInsideVisible[6]
            },
            {
              id: 7,
              visible: this.FigureLayerInsideVisible[7]
            },
            {
              id: 8,
              visible: this.FigureLayerInsideVisible[8]
            },
            {
              id: 9,
              visible: this.FigureLayerInsideVisible[9]
            },
            {
              id: 10,
              visible: this.FigureLayerInsideVisible[10]
            }
          ]
        })
        map.add(layer)

        // 创建直线测量工具
        let directLineMeasurement = new DirectLineMeasurement3D({
          view: view,
          visible: true,
          label:"距离测量"
        });
        // 创建面积测量工具
        let areaMeasurement = new AreaMeasurement3D({
          view: view,
          visible: true,
          label:"面积测量"
        });

        // 添加到视图中
        view.ui.add(directLineMeasurement, 'bottom-left')
        // 添加到视图中
        view.ui.add(areaMeasurement, 'bottom-left')

        let basemapGallery = new BasemapGallery({
          view: view
        })
        view.ui.add(basemapGallery, {
          position: 'top-right'
        })

        // 将 SceneView 对象保存到组件的 data 中
        this.sceneView = view

        // 触发 "map-ready" 事件
        this.$emit('map-ready', this.view)

        // 监听全局事件进行定位操作
        this.$eventBus.on('location', data => {
          view
            .when(function () {
              view.goTo({ center: [data[0][0], data[0][1]], zoom: data[1] })
            })
            .catch(function (err) {
              console.error('SceneView rejected:', err)
            })
        })
      } catch (error) {
        console.error('地图初始化失败：', error)
      }
    },
    toggleDirectLine() {
      this.directLineVisible = !this.directLineVisible
      if (this.directLineVisible) {
        document.getElementsByClassName('esri-direct-line-measurement-3d')[0].style.display = 'block'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, '开启距离测量'),
          duration: 1000
        })
        // document.getElementsByClassName('measure-tools-icon')[0].style.backgroundColor = 'rgba(199, 199, 199, 0.5)'
      } else {
        document.getElementsByClassName('esri-direct-line-measurement-3d')[0].style.display = 'none'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, '关闭距离测量'),
          duration: 1000
        })
        // document.getElementsByClassName('measure-tools-icon')[0].style.backgroundColor = 'rgba(199, 199, 199, 0)'
      }
    },
    toggleArea() {
      this.areaVisible = !this.areaVisible
      if (this.areaVisible) {
        document.getElementsByClassName('esri-area-measurement-3d')[0].style.display = 'block'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, '开启面积测量'),
          duration: 1000
        })
        // document.getElementsByClassName('measure-tools-icon')[1].style.backgroundColor = 'rgba(199, 199, 199, 0.5)'
      } else {
        document.getElementsByClassName('esri-area-measurement-3d')[0].style.display = 'none'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, '关闭面积测量'),
          duration: 1000
        })
        // document.getElementsByClassName('measure-tools-icon')[1].style.backgroundColor = 'rgba(199, 199, 199, 0)'
      }
    },
    //设置点击函数
    showFigureLayer() {
      this.FigureLayerVisible = !this.FigureLayerVisible
      // console.log(this);
      // console.log(this.initializeMap());
      console.log(this.FigureLayerVisible)
      // console.log(layer2.sublayers._items[0].visible);
    }
  },
  watch: {
    FigureLayerInsideVisible: {
      // immediate:true,
      deep:true,
      handler(){
        // console.log("1111");
        this.initializeMap();
      }
    }
  }
}
</script>
<style>
/* *{
  margin: 0px;
} */
body{
  margin-bottom: 0px;
}
/* 控制popupTemplate显示 */
.esri-popup__main-container {
  display: none !important;
}
/* *设置图例大小 */
.esri-legend {
  height: 200px;
}
.esri-ui-corner .esri-component.esri-widget--panel {
  width: 150px !important;
}
.esri-basemap-gallery {
  max-width: 100px !important;
  max-height: 130px !important;
}
.esri-direct-line-measurement-3d {
  display: none;
}
.esri-area-measurement-3d {
  display: none;
}
.measure-tools-wrapper {
  position: fixed;
  display: flex;
  flex-direction: row;
  left: 40px;
  top: 250px;
  transform: translateX(-50%);
}
.measure-tools {
  justify-content: center;
}
.measure-tools-icon {
  background-color: #ffffff;
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px 0;
}
/*图层按钮的样式*/
.map-FigureLayer {
  position: fixed;
  width: 60px;
  height: 40px;
  right: 22px;
  top: 200px;
  background-color: #ffffff;
  text-align: center;
  font-size: 1em;
  border-radius: 10px;
  border:2px solid skyblue;
  /* box-shadow:  1px 1px 3px 3px black; */
}
.map-FigureLayer p {
  margin: 0 auto;
  line-height: 40px;
}
.map-FigureLayer-Font :hover {
  cursor: pointer;
  user-select: none;
}

.map-FigureLayer-TF {
  position: fixed;
  right: 22px;
  top: 260px;
  background-color: #4f8ee1;
  border-radius: 10px;
  height: 400px;
  color: white;
  font-weight: bold;
  width: 225px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: stretch;
  overflow-y: scroll;
  opacity: 0.88;
  user-select:none;
}
.map-FigureLayer-TF p {
  width: 180px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.map-FigureLayer-TFdiv {
  display: flex;
}
.map-FigureLayer-TFdiv > div {
  line-height: 40px;
  flex-wrap: wrap;
  right: 20px;
  height: 40px;
  align-items: center;
}
/* 设置导航 */
.navbar {
  background-color: #4f8ee1 !important;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 1; /* 设置比地图视图层级高 */
}
.navbar-left img {
  display: inline;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 0.5px solid darkgray;
  margin-right: 10px;
}

.navbar-left {
  margin-left: 20px;
  display: flex;
  align-items: center;
}

.navbar-left h1 {
  font-size: 24px;
  font-weight: bold;
  color: #ffffff;
  margin: 0;
}

.navbar-right {
  margin-right: 20px;
  display: flex;
  align-items: center;
}

.navbar-right img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 0.5px solid darkgray;
  margin-right: 10px;
}

.navbar-right p {
  font-size: 14px;
  color: #ffffff;
  margin: 0;
}
.navbar-right p:hover {
  cursor: pointer;
  color: yellowgreen;
}
.navbar-right img:hover {
  cursor: pointer;
  width: 35px;
  height: 35px;
  transition-duration: 0.4s;
}

.measure-tools-icon:hover {
  cursor: pointer;
  background-color: rgb(243, 243, 243);
}
</style>
