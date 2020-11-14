<template>
    <div class="md_root_content" :style="{width:this.width,height: this.height}">

        <!--功能按钮区-->
        <div class="button_bar">
            <span @click="addBold"><B>B</B></span>
            <span @click="addUnderline"><B>U</B></span>
            <span @click="addItalic"><B>I</B></span>
        </div>

        <!--主要内容区-->
        <div class="content_bar">
            <!--markdown编辑器区-->
            <div class="markdown_body">
                <textarea ref="ref_md_edit" class="md_textarea_content" v-model="markString"></textarea>
            </div>

            <!--解析成html区-->
            <div class="html_body">
                <div style="width: 100%" v-html="htmlString"></div>
            </div>

        </div>
    </div>
</template>

<script>
    import marked from 'marked'     //解析mardown语法的库
    import hljs from 'highlight.js' //对代码进行语法高亮的库

    export default {
        name: "MarkDownEditor",
        props: {
            width: {
                type: String,
                default: '1000px'
            },

            height: {
                type: String,
                default: '600px'
            }
        },

        data() {
            return {
                markString: '',
                htmlString: ''
            }
        },

        mounted(){
            this.markString = ''
        },

        methods: {
            //加粗
            addBold() {
                this.changeSelectedText("**","**")
            },

            //斜体
            addItalic() {
                this.changeSelectedText("***","***")
            },

            addUnderline() {
                this.changeSelectedText("<u>","</u>")
            },

            changeSelectedText(startString,endString){
                let t = this.$refs.ref_md_edit
                if (window.getSelection) {
                    if (t.selectionStart != undefined && t.selectionEnd != undefined && t.selectionStart != t.selectionEnd) {
                        let str1 = t.value.substring(0, t.selectionStart)
                        let str2 = t.value.substring(t.selectionStart, t.selectionEnd)
                        let str3 = t.value.substring(t.selectionEnd)
                        let result = str1 + startString + str2 + endString + str3
                        t.value = result
                        this.markString = t.value
                    }
                }
            }
        },

        watch: {

            //监听markString变化
            markString: function (value) {
                marked.setOptions({
                    renderer: new marked.Renderer(),
                    gfm: true,
                    tables: true,
                    breaks: true,
                    pedantic: false,
                    sanitize: false,
                    smartLists: true,
                    smartypants: false
                })
                this.htmlString = marked(value)
            },

            //监听htmlString并对其高亮
            htmlString: function () {
                this.$nextTick(() => {

                    const codes = document.querySelectorAll(".html_body pre code");

                    // elem 是一个 object
                    codes.forEach(elem => {
                        elem.innerHTML = "<div style='width:100%;overflow:auto'><ol><li>" + elem.innerHTML.replace(/\n/g, "\n</li><li>") + "\n</li></ol></div>"
                        hljs.highlightBlock(elem);
                    });
                });
            }
        }
    }
</script>

<style scoped>
    @import "~highlight.js/styles/solarized-dark.css";
    .md_root_content {
        display: flex;
        display: -webkit-flex;
        flex-direction: column;
    }

    .button_bar {
        display: flex;
        display: -webkit-flex;

        width: 100%;
        height: 40px;
        background-color: #d4d4d4;
        align-items: center;
    }

    div.button_bar span {
        width: 30px;
        line-height: 40px;
        text-align: center;
        color: orange;
        cursor: pointer;
    }

    .content_bar {
        display: flex;
        display: -webkit-flex;
        width: 100%;
        height: 100%;
    }

    .markdown_body {
        display: flex;
        display: -webkit-flex;
        width: 50%;
        height: 100%;
    }

    .html_body {
        display: flex;
        display: -webkit-flex;

        width: 50%;
        height: 100%;
        text-align: left;
        background-color: #dfe9f1;
    }

    .md_textarea_content {
        flex: 1;
        height: 100%;
        padding: 12px;
        overflow: auto;
        box-sizing: border-box;
        resize: none;
        outline: none;
        border: none;
        background-color: #f4f4f4;
        font-size: 14px;
        color: #232323;
        line-height: 24px;
    }
</style>
