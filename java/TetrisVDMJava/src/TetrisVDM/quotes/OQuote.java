package TetrisVDM.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class OQuote {
  private static int hc = 0;
  private static OQuote instance = null;

  public OQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static OQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new OQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof OQuote;
  }

  public String toString() {

    return "<O>";
  }
}
