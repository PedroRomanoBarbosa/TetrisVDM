package TetrisVDM.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ZQuote {
  private static int hc = 0;
  private static ZQuote instance = null;

  public ZQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ZQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ZQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ZQuote;
  }

  public String toString() {

    return "<Z>";
  }
}
