<template>
    <el-select
            @keyup.enter.native="remoteMethodByKB"
            v-model="value"
            filterable
            remote
            style="width: 100%"
            :placeholder="placeholder"
            @change="change(value)"
            :remote-method="remoteMethod"
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
    export default {
        name: 'SelectPro',
        data() {
            return {
                filteredList: [],
                allData: []
            }
        },
        props: {
            options: {
                type: Object,
                default() {
                    return {
                        reskey: '',
                        value: 'value',
                        label: 'label'
                    }
                }
            },
            value: [String],
            placeholder: {
                type: String,
                default() {
                    return '请输入关键字'
                }
            },
            query: {
                type: Function,
                default() {
                    return (val) => {
                        return new Promise(resolve => {
                            resolve([])
                        })
                    }
                }
            }
        },
        watch: {
            value(val) {
                console.log(val)
            }
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
                for (let i = 0; i < list.length; i++) {
                    const item = list[i]
                    if (
                        this.options['value']
                        && typeof this.options['value']  === "string"
                        && this.options['value'] != '')
                    {
                        item['value'] = item[this.options['value']]
                    }
                    if (
                        this.options['label']
                        && typeof this.options['label']  === "string"
                        && this.options['label'] != '')
                    {
                        item['label'] = item[this.options['label']]
                    }
                }
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
                if (queryStr != '') {
                    this.query(queryStr).then(res => {
                        this.dealRes(res)
                    })
                } else {
                    this.filteredList = this.allData
                }
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

