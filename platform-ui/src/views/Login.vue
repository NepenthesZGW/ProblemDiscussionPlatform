<template>
    <div>
        <el-container>
            <el-aside width="200px">Aside</el-aside>
            <el-container>
                <el-header>Login</el-header>
                <el-main>
                    <el-form label-position="left" label-width="80px" :model="member">
                        <el-form-item label="账号">
                            <el-input v-model="member.account"></el-input>
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input type="password" v-model="member.password" :show-password = 'true'></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="doLogin()">登录</el-button>
                        </el-form-item>
                    </el-form>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                member: {
                    account: '',
                    password: '',
                }
            }
        },
        methods: {
            doLogin(){
                this.successHandle()
            },
            successHandle(){
                this.$router.push('/')
            },
            errorHandle(){

            }
        }
    }
</script>

<style scoped>

</style>
