<template>
  <el-container style="height:100vx;" direction=vertical>
    <codemirror
    :value="code"
    ref="myEditor"
    style="width:100%;border:0;"
    >
    </codemirror>
    <div style="border-left: 30px solid #FFFFFF;background-color:#363636;height: 32px;">
      <el-button type="primary" v-on:click="onClickSubmit">提交<i class="el-icon-upload el-icon--right"></i></el-button>
    </div>
  </el-container>
</template>

<script>
import { codemirror } from 'vue-codemirror-lite'

require('codemirror/mode/javascript/javascript')
require('codemirror/mode/vue/vue')
require('codemirror/mode/python/python')

require('codemirror/addon/hint/show-hint.js')
require('codemirror/addon/hint/show-hint.css')
require('codemirror/addon/hint/javascript-hint.js')
require('codemirror/addon/hint/anyword-hint.js')

export default {
  data () {
    return {
      code: '#include<stdlib.h>\n#include<stdio.h>\n\nint main(){\n    return 0;\n}'
    }
  },
  components: {
    codemirror
  },
  computed: {
    editor () {
      // get current editor object
      return this.$refs.myEditor.editor
    }
  },
  mounted () {
    // use editor object...
    this.editor.focus()
  },
  methods: {
    onClickSubmit: function () {
      this.$emit('listenToChildEvent', this.editor.getValue())
    }
  }
}
</script>

<style>

</style>
