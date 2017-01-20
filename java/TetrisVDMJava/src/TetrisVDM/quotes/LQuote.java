package TetrisVDM.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class LQuote {
  private static int hc = 0;
  private static LQuote instance = null;

  public LQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static LQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new LQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof LQuote;
  }

  public String toString() {

    return "<L>";
  }
}
