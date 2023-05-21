<template>
  <div ref="chart" style="width: 700px; height: 400px;"></div>
</template>
 
<script>
import { ref, onMounted, toRaw } from 'vue'
import * as echarts from 'echarts'
export default {
  props: ['indicator', 'targetValue'],
  setup(props) {
    const chart = ref(null)
    const options = {
      title: {
        text: '报告整体健康评价指标赋分雷达图',
        // 标题的位置
        left: 'center'
      },
      grid: {
        position: 'center'
      },
      // 提示框组件配置
      tooltip: {
        //雷达图的tooltip不会超出div，也可以设置position属性，position定位的tooltip 不会随着鼠标移动而位置变化，不友好
        confine: true,
        enterable: true //鼠标是否可以移动到tooltip区域内
      },
      // 雷达图的坐标系配置
      radar: {
        // 通过雷达图的缩放来控制上边距
        radius: '65%',
        // 雷达图指示器配置，即雷达图坐标系中的每个轴线对应的数据，是一个数组类型
        // name：指示器名称  max：指示器最大值,min：指示器最小值color：指示器线条颜色。
        indicator: toRaw(props).indicator,

        // shape：雷达图绘制方式，可选值为 "polygon"（多边形）或 "circle"（圆形），默认为 "polygon"。
        shape: 'polygon',
        // splitNumber：雷达图刻度数量，如果是单个数字，表示圆周方向和半径方向的刻度数量相同；
        // 如果是长度为2的数组，第一个数字表示圆周方向的刻度数量，第二个数字表示半径方向的刻度数量，默认为 5。
        splitNumber: 5,
        // name：雷达图名称样式配置
        name: {
          textStyle: {
            color: '#000'
          }
        }
      },
      series: [
        {
          name: '报告整体健康评价指标赋分雷达图',
          type: 'radar',
          data: [
            {
              value: toRaw(props).targetValue,
              name: '对应指标赋分',
              // 设置区域边框的颜色
              itemStyle: {
                normal: {
                  color: 'rgba(205,92,92,1)'
                }
              },
              // 设置区域的颜色
              areaStyle: {
                normal: {
                  color: 'rgba(205,92,92,0.3)'
                }
              },
              // 在拐点处设置数值
              label: {
                normal: {
                  show: true
                }
              }
            }
          ]
        }
      ]
    }
    // mounted钩子函数
    onMounted(() => {
      const chartInstance = echarts.init(chart.value)
      chartInstance.setOption(options)
    })

    return { chart, options }
  }
}
</script>
 
<style scoped>
</style>