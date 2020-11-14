<template>
    <div>
        <el-form ref="form" :model="problem" label-width="80px">
            <el-form-item label="问题标题">
                <el-input v-model="problem.title"></el-input>
            </el-form-item>
            <el-form-item label="问题类型">
                <el-select v-model="problem.classify" placeholder="请选择问题类型">
                    <el-option label="技术类" value="technology"></el-option>
                    <el-option label="生活类" value="life"></el-option>
                </el-select>
            </el-form-item>
<!--            <el-form-item label="问题">-->
<!--                <el-col :span="11">-->
<!--                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>-->
<!--                </el-col>-->
<!--                <el-col class="line" :span="2">-</el-col>-->
<!--                <el-col :span="11">-->
<!--                    <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>-->
<!--                </el-col>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="是否">-->
<!--                <el-switch v-model="form.delivery"></el-switch>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="活动性质">-->
<!--                <el-checkbox-group v-model="form.type">-->
<!--                    <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>-->
<!--                    <el-checkbox label="地推活动" name="type"></el-checkbox>-->
<!--                    <el-checkbox label="线下主题活动" name="type"></el-checkbox>-->
<!--                    <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>-->
<!--                </el-checkbox-group>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="特殊资源">-->
<!--                <el-radio-group v-model="form.resource">-->
<!--                    <el-radio label="线上品牌商赞助"></el-radio>-->
<!--                    <el-radio label="线下场地免费"></el-radio>-->
<!--                </el-radio-group>-->
<!--            </el-form-item>-->
            <el-form-item label="问题描述">
                <el-input type="textarea" v-model="problem.content"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
            </el-form-item>
        </el-form>

        <MarkDownEditor width="100%"></MarkDownEditor>
    </div>
</template>

<script>
    import MarkDownEditor from '@/components/MarkDownEditor'
    export default {
        name: "RecordProblem",
        components: {MarkDownEditor},
        data() {
            return {
                problem: {
                    title: '',
                    classify: '',
                    content: ''
                }
            }
        },
        methods: {
            onSubmit(){

            }
        }
    }
</script>

<style scoped>

</style>
