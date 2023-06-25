<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 40px">
      <el-col :span="8">
        <el-card style="color: #409EFF">
          <div>药品种类</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">{{ drug_kind }}种</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card style="color: #67C23A">
          <div>库存总量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">{{ drug_num }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card style="color: #F56C6C">
          <div>库存价值</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">{{ drug_amount }}元</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="margin-bottom: 40px">
        <el-card id="bar" style="height: 400px"></el-card>
      </el-col>
    </el-row>
    <el-row :gutter="40">
      <el-col :span="12">
        <el-card id="line" style="height: 300px"></el-card>
      </el-col>
      <el-col :span="12">
        <el-card id="pie" style="height: 300px"></el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "Charts",
  data(){
    return{
      drug_kind: 0,
      drug_num: 0,
      drug_amount: 0
    }
  },
  created() {
    this.get_drug_kind()
    this.get_drug_num()
    this.get_drug_amount()
  },
  mounted: function () {  //页面元素渲染之后再触发
    var barOption = {
      title: {
        text: '各药品库存余量统计',
        subtext: '柱状图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        axisLabel: {
          interval: 0,
          rotate:40,
        },
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: "",
          data: [],
          type: 'bar'
        }
      ]
    };
    var barDom = document.getElementById('bar');
    var barChart = echarts.init(barDom);

    //折线图
    var lineOption = {
      title: {
        text: '各季度出入库数量统计',
        subtext: '趋势图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ["第一季度", "第二季度", "第三季度", "第四季度"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: "入库数量",
          data: [],
          type: 'line'
        },
        {
          name: "出库数量",
          data: [],
          type: 'line'
        }
      ]
    };
    var lineDom = document.getElementById('line');
    var lineChart = echarts.init(lineDom);

    //饼图
    var pieOption = {
      title: {
        text: '各季度出入库数量统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '入库数量',
          type: 'pie',
          radius: '50%',
          center: ['35%', '50%'],
          label: {            //饼图图形上的文本标签
            normal: {
              show: true,
              position: 'inner', //标签的位置
              textStyle: {
                fontWeight: 300,
                fontSize: 14,    //文字的字体大小
                color: "#fff"
              },
              formatter: '{d}%'
            }
          },
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },
        {
          name: '出库数量',
          type: 'pie',
          radius: '50%',
          center: ['65%', '50%'],
          label: {            //饼图图形上的文本标签
            normal: {
              show: true,
              position: 'inner', //标签的位置
              textStyle: {
                fontWeight: 300,
                fontSize: 14,    //文字的字体大小
                color: "#fff"
              },
              formatter: '{d}%'
            }
          },
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);

    this.request.get("/echarts/num").then(res => {
      // 填空  折线图
      lineOption.series[0].data = res.data.slice(0, 4)
      lineOption.series[1].data = res.data.slice(4, 8)
      lineChart.setOption(lineOption);

      //饼图
      pieOption.series[0].data = [
        {name: "第一季度", value: res.data[0]},
        {name: "第二季度", value: res.data[1]},
        {name: "第三季度", value: res.data[2]},
        {name: "第四季度", value: res.data[3]},
      ]
      pieOption.series[1].data = [
        {name: "第一季度", value: res.data[4]},
        {name: "第二季度", value: res.data[5]},
        {name: "第三季度", value: res.data[6]},
        {name: "第四季度", value: res.data[7]},
      ]
      pieChart.setOption(pieOption);
    })

    this.request.get("/echarts/bar").then(res => {
      // 填空  柱状图
      barOption.xAxis.data = Object.keys(res.data)
      barOption.series[0].data = Object.values(res.data)
      barChart.setOption(barOption)
    })

  },
  methods: {
    get_drug_kind(){
      this.request.get("/echarts/drug_kind").then(res => {
        this.drug_kind = res.data
      })
    },
    get_drug_num(){
      this.request.get("/echarts/drug_num").then(res => {
        this.drug_num = res.data
      })
    },
    get_drug_amount(){
      this.request.get("/echarts/drug_amount").then(res => {
        this.drug_amount = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>