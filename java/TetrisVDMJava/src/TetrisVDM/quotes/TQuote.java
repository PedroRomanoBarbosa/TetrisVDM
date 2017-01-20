package TetrisVDM.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TQuote {
  private static int hc = 0;
  private static TQuote instance = null;

  public TQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TQuote;
  }

  public String toString() {

    return "<T>";
  }
}
