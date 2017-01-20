package TetrisVDM.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SQuote {
  private static int hc = 0;
  private static SQuote instance = null;

  public SQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SQuote;
  }

  public String toString() {

    return "<S>";
  }
}
