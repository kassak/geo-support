package com.github.kassak.geo;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.util.ExceptionUtil;
import org.geotools.data.wmts.WebMapTileServer;
import org.geotools.data.wmts.model.WMTSLayer;
import org.geotools.map.Layer;
import org.geotools.map.WMTSMapLayer;
import org.geotools.swing.JMapPane;
import org.geotools.swing.SingleLayerMapContent;
import org.geotools.swing.tool.PanTool;
import org.geotools.swing.tool.ScrollWheelTool;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.List;

public class MapToolWindowFactory implements ToolWindowFactory {
  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//    WebMapServer wms = null;
//    try {
//      wms = new WebMapServer(
//        new URL("http://atlas.gc.ca/cgi-bin/atlaswms_en?VERSION=1.1.1&Request=GetCapabilities&Service=WMS"));
//    } catch (Exception e) {
//      ExceptionUtil.rethrowAllAsUnchecked(e);
//    }
//    Layer layer = new WMSLayer(wms, wms.getCapabilities().getLayer());
    WebMapTileServer wms = null;
    try {
      wms = new WebMapTileServer(
        new URL("https://gibs.earthdata.nasa.gov/wmts/epsg4326/best/wmts.cgi?VERSION=1.0.0&Request=GetCapabilities&Service=WMTS"));
    } catch (Exception e) {
      ExceptionUtil.rethrowAllAsUnchecked(e);
    }

    List<WMTSLayer> layers = wms.getCapabilities().getLayerList();
    for (int i = 0; i < layers.size(); i++) {
      System.out.println(i + " " + layers.get(i).getName());
    }

    Layer layer = new WMTSMapLayer(wms, layers.get(0));
    JMapPane pane = new JMapPane(new SingleLayerMapContent(layer));
    pane.addMouseListener(new ScrollWheelTool(pane));
    PanTool panTool = new PanTool();
    panTool.setMapPane(pane);
    pane.addMouseListener(panTool);
    toolWindow.getComponent().add(pane);
  }

  @Override
  public boolean isDoNotActivateOnStart() {
    return true;
  }
}
