<template>
    <el-select
            remote
            :remote-method="remoteMethod"
            @keyup.enter.native="remoteMethodByKB"
            v-model="needValue"
            filterable
            :style="css"
            :placeholder="placeholder"
            @change="change(needValue)"
    >
        <el-option
                v-for="item in filteredList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
        >
        </el-option>
    </el-select>
</template>

<script>
    const dealList = (list,options)=>{
        for (let i = 0; i < list.length; i++) {
            const item = list[i]
            if (
                options['value']
                && typeof options['value']  === "string"
                && options['value'] != '')
            {
                item['value'] = item[options['value']]
            }
            if (
                options['label']
                && typeof options['label']  === "string"
                && options['label'] != '')
            {
                item['label'] = item[options['label']]
            }
        }
        return list
    }
    export default {
        name: 'SelectPro',
        data() {
            return {
                filteredList: dealList(this.initlist,this.options),
                allData: [],
                needValue: this.vmodel
            }
        },
        model: {
            prop: 'vmodel',
            event: 'vmodelchange'
        },
        props: {
            css: {
                type: String,
                default() {
                    return 'width: 100%'
                }
            },
            initlist: {
                type: Array,
                default() {
                    return []
                }
            },
            options: {
                type: Object,
                default() {
                    return {
                        reskey: '',
                        value: '',
                        label: ''
                    }
                }
            },
            vmodel: [String],
            placeholder: {
                type: String,
                default() {
                    return '请输入关键字'
                }
            },
            query: {
                type: Function,
                default() {
                    return () => {
                        return new Promise(resolve => {
                            resolve([])
                        })
                    }
                }
            }
        },
        watch: {
            initlist(val) {
              if (val === undefined) {
                  this.filteredList = []
              }
              if (val == null){
                  this.filteredList = []
              }
              this.mergeArray(dealList(val,this.options),this.filteredList, 'value')
            },
            needValue(val) {
                this.$emit('vmodelchange', val)
            },
            allData(val) {
                this.$emit('alldatachange', val)
            },
            filteredList(val) {
                this.mergeArray(val, this.allData, 'value')
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
                this.allData  = this.mergeArray(list, this.allData,'value');
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
                let queryStr = event.target.value
                if (queryStr === undefined) queryStr = ''
                if (queryStr == null) queryStr = ''
                // if (queryStr != '') {
                //     this.query(queryStr).then(res => {
                //         this.dealRes(res)
                //     })
                // } else {
                //     this.filteredList = this.allData
                // }
                this.query(queryStr).then(res => {
                    this.dealRes(res)
                })
            },
            remoteMethod(query) {
                if (query != '') {
                    this.filteredList = this.allData.filter(item => {
                        return item.label.toLowerCase()
                            .indexOf(query.toLowerCase()) > -1;
                    });
                    if (this.filteredList.length == 0){
                        this.query(query).then(res => {
                            this.dealRes(res)
                        })
                    }
                } else {
                    this.filteredList = this.allData
                }
            },
            change(event) {
                this.$emit('change', event)
            }
        }
    }
</script>

<style scoped>

</style>

