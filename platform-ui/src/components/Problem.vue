<template>
    <div style="width: 100%" class="md_html_body">
        <div  v-html="mdHtml"></div>
    </div>
</template>

<script>
    export default {
        name: "Problem",
        props:{
            mdHtml:[String]
        }
    }
</script>

<style scoped>
    @import "~highlight.js/styles/solarized-dark.css";
</style>
