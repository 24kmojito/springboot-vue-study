<template>
  <div class="whole">
     <div class="head1">
       <a-menu mode="horizontal"  :style="{ lineHeight: '58px' ,float:'left',borderColor:'dimgray'}">
         <a-menu-item key="1">
           <router-link to="/main">
             <a-icon type="home" />
             <span>主页</span>
           </router-link>
         </a-menu-item>
         <a-menu-item key="2"><!--key值用于设置默认导航-->
           <router-link to="/add">
             <a-icon type="folder-add" />
             <span>添加简历</span>
           </router-link>
         </a-menu-item>
         <a-menu-item key="3">
           <router-link to="/myView">
             <a-icon type="eye" />
             <span>查看简历</span>
           </router-link>
         </a-menu-item>
         <a-menu-item key="4">
           <router-link to="/modify">
             <a-icon type="edit" />
             <span>修改简历</span>
           </router-link>
         </a-menu-item>
         <a-menu-item key="5">
           <a-input id="query" type="text" v-model="searchKey"/>
           <a-button id="search" @click="getUsers()">Search</a-button>
         </a-menu-item>
         <a-menu-item key="6">
           <router-link to="/news">
             <a-icon type="mail"/>
             <span>消息</span>
           </router-link>
         </a-menu-item>
         <a-menu-item key="7">
           <a-icon type="appstore"/>
           <span>商城</span>
         </a-menu-item>
         <a-menu-item key="8">
           <router-link to="/setting">
             <a-icon type="setting"/>
             <span>设置</span>
           </router-link>
         </a-menu-item>
         <a-menu-item key="9">
           <router-link to="/write">
             <a-icon type="edit"/>
             <span>记事本</span>
           </router-link>
         </a-menu-item>
       </a-menu>

       <a-dropdown class="theLogo">
         <a class="ant-dropdown-link">
           <div class="logo"/>
         </a>
         <a-menu slot="overlay":style="{marginRight:'25px'}">
           <a-menu-item>
             <router-link to="/personal">
               <span>个人中心</span>
             </router-link>
           </a-menu-item>
           <a-menu-item>
             <span @click="exit">退出登录</span>
           </a-menu-item>
         </a-menu>
       </a-dropdown>
     </div>
     <div class="head2">
           <div id="line1"></div>
           <div class="sort">
             <div class="type1">
               <span @click="sort(1)" >技术
                 <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
                 <!--outline为点击按钮后边框颜色-->
               </span>
               <span @click="sort(2)" style="margin-left: 30px">学历
                    <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">劳动
                  <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">外语
                    <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">征信
                   <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">高校
                    <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">性别
                   <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">预期工资
                   <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
             </div>
             <div class="type2">
               <span>劳动
                   <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">职称
                  <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">劳动
                    <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">高校
                  <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">劳动
                    <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">高校
                   <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">年龄
                    <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
               <span style="margin-left: 30px">工作年数
                   <div style="border-color:rgba(215, 223, 226, 0); width: 35px;height: 15px;
                 background-color:lightskyblue;outline:none;display: inline;border-radius: 2px">
                   <span style="font-size: 10px">999+</span>
                 </div>
               </span>
             </div>
           </div>
       <div id="line2"></div>
     </div>
     <div class="content">
       <a-table  bordered :dataSource="userList" :columns="columns"
                 :rowKey="columns[0].key":pagination="pagination":scroll="{y:500}"><!--y为列表的高度-->

         <template slot="operate" slot-scope="text,record">
           <a-button  @click="detail(record.s_id)">查看简历</a-button>
         </template>
       </a-table>
     </div>
  </div>
</template>
<script>
import {mapMutations} from "vuex";
export default {
  data() {
    return {
      searchKey:'',
      myid:'',
      collapsed: false,
      userList: this.getAllStaffs(),
      sort_key:'',
      sortList:['985','技术'],

      pagination:{/*ant design 分页器*/
        current:1,
        pageNo: 1,
        pageSize: 5, // 默认每页显示数量
        showSizeChanger: true, // 显示可改变每页数量
        pageSizeOptions: ['5','10','15','20'], // 每页数量选项
        onChange: (current, pageSize) => {
          this.pagination.current=current;
          this.pagination.pageSize=pageSize;
        },//点击页码事件

        total: 0 //总条数
      },
      columns:[
        {
          title: '员工id',
          dataIndex: 's_id',
          key:'id',
          width: 100
        },
        {
          title: '姓名',
          dataIndex: 's_name',
          key:'name',
          width: 100
        },
        {
          title: '员工性别',
          dataIndex: 's_sex',
          key:'sex',
          width: 100
        },
        {
          title: '员工年龄',
          dataIndex: 's_age',
          key:'age',
          width: 100
        },
        {
          title: '原公司',
          dataIndex: 's_everemploy',
          key:'everemploy',
          width: 100
        },
        {
          title: '操作',//单引号与双引号有区别
          dataIndex: 'operate',
          key: 'operation',
          scopedSlots:{customRender:'operate'},
          width: 100
        },
      ]
    };
  },
  methods: {
    ...mapMutations(['delToken']),
    getAllStaffs: function () {
      var _this = this;
      _this.$axios.post('/api/user/getStaff')
          .then(function (response) {
            console.log(response.data.data)
            _this.userList = response.data.data;
            //_this.pagination.total=response.data.data.total;
          }).catch(function (error) {
        console.log(error);
      });
    },
    getUsers: function () {
      var _this = this;
      _this.$axios.post('/api/user/getStaffByName',_this.$qs.stringify({
        searchKey:this.searchKey
      }))
          .then(function (response) {
            _this.userList = response.data.data;
          }).catch(function (error) {
        console.log(error);
      });
    },
    exit:function (){
      this.delToken({token:''});
      this.$router.push('/login');
    },
    detail:function (value)
    {
       var _this=this;
       //console.log(value);
      _this.$router.push({path:"view/",query:{key:value}})
    },
    sort:function (value) {
      var _this=this;
      _this.sort_key=_this.sortList[value-1]
      _this.$router.push({path:"sort/",query:{key:_this.sort_key}})
    },
  }
}
</script>
<style>
.whole{
  height: 750px;
  background-color: white;
}
.head1{
  height: 60px;
  border-bottom: 1px solid dimgray;
}
.head2{
  height: 100px;
  margin-top: 10px;

  margin-right: 100px;
  margin-left: 100px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  background-color: white;
  border: 1px solid black;
}
.content{
  height:580px ;
  margin-right: 100px;
  margin-left: 100px;
  border: 1px solid dimgray;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: white;
}
#line1{
  display: inline-block;
  height: 50px;
  width: 1px;
  background-color:lightslategray;
  margin-top: 25px;
  margin-bottom: 25px;
  margin-left: 280px;
  vertical-align: top;
}
.sort{
  display: inline-block;
  height: 100px;
  vertical-align: top;
}
.type1{
  height: 30px;
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 20px;
}
.type2{
  height: 30px;
  margin-left: 20px;
  margin-right: 20px;
  margin-buttom: 20px;
}
#line2{
  display: inline-block;
  height: 50px;
  width: 2px;
  background-color:lightslategray;
  margin-top: 25px;
  margin-bottom: 25px;
  margin-right: 200px;
  vertical-align: top;
}
.theLogo{
  float: right;
  padding-right: 50px;
}
.logo{
  float: right;
  background: url("../assets/hh.jpg");
  border-radius: 50%;
  width: 40px;height: 40px;
  margin-top: 10px;
  background-size: 40px;
}
#query{
  display: inline-block;
  width:450px;
}
#search{
  display: inline-block;
}
/*////////////////////////////*/
</style>