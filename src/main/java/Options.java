import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

import java.util.List;

public class Options extends OptionsBase {

    /*@Option(
            name = "help",
            abbrev = 'h',
            help = "Prints usage info.",
            defaultValue = "true"
    )
    public boolean help;
*/
    /*@Option(
            name = "input",
            abbrev = 'i',
            help = "The input for search.",
            category = "startup",
            defaultValue = ""
    )
    public String input;
    */
    @Option(
            name = "results",
            abbrev = 'r',
            help = "Definition of the number of results. Possible amount of results are 1-6.",
            category = "startup",
            defaultValue = "3"
    )
    public int results;

    @Option(
            name = "search",
            abbrev = 's',
            help = "Definition of the search engine. Available search engines are google and bing.",
            category = "startup",
            defaultValue = "google"
    )
    public String search;



}