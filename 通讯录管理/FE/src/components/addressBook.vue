<template>
  <el-col :span="16" :offset="4">
    <el-card style="background:#ebebeb;">
      <p style="font-size: 20px">通讯录管理</p>
      <el-alert title="刷新可获得当前数据库全部数据(可关闭)" style="width:550px;margin:0 auto;" type="warning"></el-alert>
      <el-input
        placeholder="请输姓名查询"
        prefix-icon="el-icon-search"
        v-model="input"
        style="width: 320px;margin-top: 30px;margin-bottom: 20px;"
      ></el-input>
      <el-button type="primary" :plain="true" icon="el-icon-search" @click="searchbyname">搜索</el-button>
      <el-button type="primary" icon="el-icon-arrow-down" @click="sort">按姓名排序</el-button>
      <el-card style="width: 560px;margin: 0 auto 50px auto;">
        <el-table :data="elTable" max-height="250" style="background:#fff;">
          <el-table-column fixed prop="name" label="姓名" width="150"></el-table-column>
          <el-table-column fixed prop="tel" label="联系电话" width="150"></el-table-column>
          <el-table-column fixed="right" label="操作" width="120">
            <template slot-scope="scope">
              <el-button
                @click.native.prevent="deleteRow(scope.$index, friendTable)"
                type="text"
                size="small"
              >移除</el-button>
              <el-button @click="dialogFormVisible = true" type="text" size="small">添加</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="插入好友信息" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-input class="dialog-input" v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input class="dialog-input" v-model="form.tel" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" :plain="true" @click="insert">确 定</el-button>
          </div>
        </el-dialog>
      </el-card>
    </el-card>
  </el-col>
</template>

<script>
import query from "@/api/query";
export default {
  name: "addressBook",
  data() {
    return {
      input: "",
      dialogFormVisible: false,
      elTable: [{ name: "", tel: "" }],
      friendTable: [{ name: "", tel: "" }],
      form: {
        name: "",
        tel: ""
      },
      formLabelWidth: "150px"
    };
  },
  mounted() {
    this.queryAll();
  },
  methods: {
    queryAll() {
      let that = this;
      query
        .search()
        .then(function(resp) {
          if (resp.data.code == 200) {
            that.friendTable = resp.data.data;
            that.elTable = that.friendTable;
          }
        })
        .catch(function(err) {
          console.log(err);
        });
    },
    searchbyname(input) {
      let that = this;
      let id;
      for (let i = 0; i < that.friendTable.length; i++) {
        if (this.input == that.friendTable[i].name) {
          id = that.friendTable[i].id;
        }
      }
      if (id) {
        query
          .searchbyid(id)
          .then(function(resp) {
            if (resp.data.code == 200) {
              that.elTable = resp.data.data;
            }
          })
          .catch(function(err) {
            console.log(err);
          });
      } else {
        this.$message({
          showClose: true,
          message: "网络错误 或 搜索为空、搜索姓名不存在，请重试",
          type: "error"
        });
      }
    },

    deleteRow(index, rows) {
      let that = this;
      console.log(this.friendTable[index].name);
      query
        .deletebyid(this.friendTable[index].id)
        .then(
          that.$message({ message: "删除成功", type: "success" }),
          rows.splice(index, 1)
        )
        .catch(function(err) {
          console.log(err);
        });
    },
    insert() {
      let that=this;
      this.dialogFormVisible = false;
      if(that.form.name&&that.form.tel){
        query
        .insertinfo(that.form.name, that.form.tel)
        .then(
          that.$message({
            showClose: true,
            message: "插入成功，请刷新后查看",
            type: "success"
          })
        )
        .catch(function(err) {
          console.log(err);
          that.$message({ showClose: true, message: "失败", type: "error" });
        });
      } else{
          that.$message({ showClose: true, message: "插入失败，插入内容不能为空", type: "error" });
      }
    },
    sort() {
      query.sort();
      this.queryAll();
    }
  }
};
</script>

<style scoped>
.dialog-input {
  width: 350px;
}
</style>
