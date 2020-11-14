<template>
    <el-autocomplete
            ref="inputPro"
            :disabled="disabled"
            :clearable="clearable"
            :fetch-suggestions="remoteMethod"
            @keyup.enter.native="remoteMethodByKB"
            @select="select"
            :placeholder="placeholder"
            :style="css"
            v-model="needValue"
    ></el-autocomplete>
</template>

<script>
    const dealList = (list,options)=>{
        for (let i = 0; i < list.length; i++) {
            const item = list[i]
            if (
                options['id']
                && (typeof options['id']  === "string" || typeof options['id']  === "number"  )
                && options['id'] != '')
            {
                item['id'] = item[options['id']]
            }
            if (
                options['value']
                && (typeof options['value']  === "string" || typeof options['value']  === "number"  )
                && options['value'] != '')
            {
                item['value'] = item[options['value']]
            }
        }
        return list
    }
    export default {
        name: 'InputPro',
        data() {
            return {
                filteredList: [],
                allData: [],
                needValue: this.vmodel
            }
        },
        model: {
            prop: 'vmodel',
            event: 'vmodelchange'
        },
        props: {
            clearable: {
                type: Boolean,
                default() {
                    return true
                }
            },
            disabled: {
                type: Boolean,
                default() {
                    return false
                }
            },
            css: {
                type: String,
                default() {
                    return 'width: 100%'
                }
            },
            options: {
                type: Object,
                default() {
                    return {
                        reskey: '',
                        id: '',
                        value: ''
                    }
                }
            },
            vmodel: [String],
            placeholder: {
                type: String,
                default() {
                    return '请输入'
                }
            },
            query: {
                type: Function,
                default() {
                    return (val) => {
                        console.log(val)
                        return new Promise(resolve => {
                            resolve([])
                        })
                    }
                }
            }
        },
        watch: {
            needValue(val) {
                this.$emit('vmodelchange', val)
            },
            allData(val) {
                this.$emit('alldatachange', val)
            },
            filteredList(val) {
                this.mergeArray(val, this.allData, 'id')
                this.$emit('filteredlistchange', val)
            }
        },
        created() {
            if (this.needValue === undefined) this.needValue = ''
            if (this.needValue == null) this.needValue = ''
        },
        methods: {
            dealRes(res) {
                let list = []
                if (
                    this.options['reskey'] === undefined ||
                    this.options['reskey'] == null ||
                    this.options['reskey'] === ''
                ) {
                    if (res && typeof res.length === 'number' && res.length > 0) {
                        list = res
                    }
                } else {
                    if (res[this.options['reskey']] && typeof res[this.options['reskey']].length === 'number' && res[this.options['reskey']].length > 0) {
                        list = res[this.options['reskey']]
                    }
                }
                dealList(list, this.options)
                this.filteredList = list
            },
            mergeArray(list1, list2, key){
                for (let i = 0; i < list1.length; i++) {
                    let flag = true
                    for (let j = 0; j < list2.length; j++) {
                        if (list2[j][key] == list1[i][key])
                        {
                            flag = false
                            break
                        }
                    }
                    if (flag) {
                        list2.push(list1[i])
                    }
                }
                return list2
            },
            remoteMethodByKB(event) {
                console.log("回车及搜索")
                let queryStr = event.target.value
                if (queryStr === undefined) queryStr = ''
                if (queryStr == null) queryStr = ''
                if (queryStr != '') {
                    this.query(queryStr).then(res => {
                        this.dealRes(res)
                        this.$refs['inputPro'].suggestions =   this.filteredList
                    })
                } else {
                    this.filteredList = this.allData
                    this.$refs['inputPro'].suggestions =   this.filteredList
                }
            },

            remoteMethod(query,cb) {
                console.log("输入及搜索")
                if (query != '') {
                    this.filteredList = this.allData.filter(item => {
                        return item.value.toLowerCase()
                            .indexOf(query.toLowerCase()) > -1;
                    });
                    if (this.filteredList.length == 0){
                        this.query(query).then(res => {
                            this.dealRes(res)
                            cb(this.filteredList)
                        })
                    }
                } else {
                    this.filteredList = this.allData
                    cb(this.filteredList)
                }
            },
            select(item) {
                this.$emit('select', item)
            }
        }
    }
</script>

<style scoped>

</style>

