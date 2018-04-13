<template>
  <codemirror v-model="code"></codemirror>
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
    console.log('this is current editor object', this.editor)
  }
}
</script>

<style>

</style>
