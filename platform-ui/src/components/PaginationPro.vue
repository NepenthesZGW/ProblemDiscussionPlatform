<template>
    <el-pagination
            :page-sizes="pageSizesPro"
            :page-size="pageSizePro"
            :current-page="currentPagePro"
            :total="total"
            :layout="layout"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
    >
    </el-pagination>
</template>

<script>
    export default {
        name: 'PaginationPro',
        data() {
            return {
                total: 0,
                pageSizePro: this.pageSize,
                currentPagePro: this.currentPage,
                pageSizesPro: this.pageSizes,
                allListPro: this.allList,
                tempList: this.list
            }
        },
        model: {
            prop: 'list',
            event: 'listchange'
        },
        props: {
            list: {
                type: Array,
                default() {
                    return []
                }
            },
            allList: {
                type: Array,
                default() {
                    return []
                }
            },
            pageSizes: {
                type: Array,
                default() {
                    return [5, 10, 15, 20]
                }
            },
            pageSize: {
                type: Number,
                default() {
                    return 5
                }
            },
            layout: {
                type: String,
                default() {
                    return "total, sizes, prev, pager, next, jumper"
                }
            },
            currentPage: {
                type: Number,
                default() {
                    return 1
                }
            }
        },
        watch: {
            tempList(val) {
                if (val != undefined && val != null && typeof val.length == "number") {
                    this.$emit('listchange', val)
                } else {
                    this.$emit('listchange', [])
                }
            },
            allList(val) {
                // 为了改变 allListPro
                if (val != undefined && val != null && typeof val.length == "number") {
                    this.allListPro = val
                } else {
                    this.allListPro = []
                }
            },
            allListPro(val) {
                // 数据变化，进行跳转到指定页面
                if (val != undefined && val != null && typeof val.length == "number") {
                    this.total = val.length
                    this.pageJump(this.currentPagePro)
                } else {
                    this.total = 0
                }
            },
            // eslint-disable-next-line no-unused-vars
            pageSizePro(val) {
                if (this.currentPagePro == 1) {
                    this.pageJump(1)
                } else {
                    this.currentPagePro = 1
                }
            },
            currentPagePro(val) {
                this.pageJump(val)
            }
        },
        created() {
            if (this.tempList == undefined) {
                this.tempList = []
            }
            if (this.tempList == null) {
                this.tempList = []
            }
            if (typeof this.tempList.length != 'number') {
                this.tempList = []
            }

            if (this.allListPro == undefined) {
                this.allListPro = []
            }
            if (this.allListPro == null) {
                this.allListPro = []
            }
            if (typeof this.allListPro.length != 'number') {
                this.allListPro = []
            }

            if (this.pageSizesPro == undefined) {
                this.pageSizesPro = [5, 10, 15, 20]
            }
            if (this.pageSizesPro == null) {
                this.pageSizesPro = [5, 10, 15, 20]
            }
            if (typeof this.pageSizesPro.length != 'number') {
                this.pageSizesPro = [5, 10, 15, 20]
            }

            if (typeof this.currentPagePro != "number") {
                this.currentPagePro = 1
            }
            if (typeof this.pageSizePro != "number") {
                this.pageSizePro = 5
            }


        },
        method: {
            handleSizeChange(val) {
                this.pageSizePro = val
            },
            handleCurrentChange(val) {
                this.currentPagePro = val
            },
            pageJump(num) {
                let temp = []
                if (num < 1 || num > Math.ceil(this.total / this.pageSizePro)) {
                    if (this.currentPagePro > 1){
                        this.currentPagePro = this.currentPagePro -1
                    }
                    this.tempList = temp
                    return
                }
                let start = (num - 1) * this.pageSizePro
                for (let i = start; i < Math.min(start + this.pageSizePro, this.total); i++) {
                    temp.push(this.allListPro[i])
                }
                this.tempList = temp
            }
        }
    }
</script>

<style scoped>

</style>
