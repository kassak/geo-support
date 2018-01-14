package com.github.kassak.geo;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.util.ExceptionUtil;
import org.geotools.data.wms.WebMapServer;
import org.geotools.map.Layer;
import org.geotools.map.WMSLayer;
import org.geotools.swing.JMapPane;
import org.geotools.swing.SingleLayerMapContent;
import org.geotools.swing.tool.ScrollWheelTool;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

public class MapToolWindowFactory implements ToolWindowFactory {
  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    WebMapServer wms = null;
    try {
      wms = new WebMapServer(
        new URL("http://atlas.gc.ca/cgi-bin/atlaswms_en?VERSION=1.1.1&Request=GetCapabilities&Service=WMS"));
    } catch (Exception e) {
      ExceptionUtil.rethrowAllAsUnchecked(e);
    }
    Layer layer = new WMSLayer(wms, wms.getCapabilities().getLayer());
    JMapPane pane = new JMapPane(new SingleLayerMapContent(layer));
    pane.addMouseListener(new ScrollWheelTool(pane));
    toolWindow.getComponent().add(pane);
  }

  @Override
  public boolean isDoNotActivateOnStart() {
    return true;
  }
}
