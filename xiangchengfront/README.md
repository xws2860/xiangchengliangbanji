# Vue 3 + Vite

This template should help get you started developing with Vue 3 in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

## Recommended IDE Setup

- [VS Code](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## 开发日志

- 2023.04.28 ruan 解决点击无法触发跳转到地图某区域的问题，参照官方描述goTo(),需要当地图准备好之后在执行, view.when中执行
```
// create a SceneView instance (for 3D viewing)
const view = new SceneView({
  map: new Map({
    basemap: "topo-vector"
  }),
  container: "viewDiv"
});

view.when(function() {
  // SceneView is now ready for display and can be used. Here we will
  // use goTo to view a particular location at a given zoom level, camera
  // heading and tilt.
  view.goTo({
    center: [-112, 38],
    zoom: 13,
    heading: 30,
    tilt: 60
  })
})
.catch(function(err) {
  // A rejected view indicates a fatal error making it unable to display,
  // this usually means that WebGL is not available, or too old.
  console.error("SceneView rejected:", err);
});
```

