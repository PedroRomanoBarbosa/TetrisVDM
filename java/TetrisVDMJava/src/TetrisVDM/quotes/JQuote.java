package TetrisVDM.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class JQuote {
  private static int hc = 0;
  private static JQuote instance = null;

  public JQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static JQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new JQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof JQuote;
  }

  public String toString() {

    return "<J>";
  }
}
