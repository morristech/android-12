/*
 * Copyright (c) 2015 IRCCloud, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.irccloud.android.test;

import android.test.AndroidTestCase;
import android.text.Spanned;

import com.irccloud.android.ColorFormatter;

public class FormattingTests extends AndroidTestCase {

	public void testIrc_to_html() {
		assertEquals("<irc>First <font color=\"#FF0000\">1Red</font></irc>",
				ColorFormatter.irc_to_html("First \u000341Red"));

		assertEquals("<irc>First <font color=\"#808080\">1Grey</font></irc>",
				ColorFormatter.irc_to_html("First \u0003141Grey"));

		assertEquals("<irc>First <_bg800000><font color=\"#FF0000\">1Test</font></_bg800000></irc>",
				ColorFormatter.irc_to_html("First \u00034,51Test"));

		assertEquals("<irc>First <_bg808080><font color=\"#800000\">1Test</font></_bg808080></irc>",
				ColorFormatter.irc_to_html("First \u00035,141Test"));

		assertEquals("<irc>First <font color=\"#FF0000\">Red </font><font color=\"#008000\">Green</font><b> Bold</b></irc>",
				ColorFormatter.irc_to_html("First \u00034Red \u00033Green\u0003\u0002 Bold\u0002\u000f"));

		assertEquals("<irc>First <font color=\"#FF0000\">Color</font><b> Bold</b> unnecessary: <font color=\"#FF0000\">Color</font>  plain <font color=\"#FF0000\">Color</font> <b>Bold</b> <font color=\"#FF0000\">No space color</font><font color=\"#FF0000\"> New color</font></irc>",
				ColorFormatter.irc_to_html("First \u00034Color\u0003\u0002 Bold\u0002 unnecessary:\u0003 \u00034Color\u0003\u0000 plain \u00034Color\u0003\u000f \u0002Bold\u000f \u00034No space color\u0003\u00034 New color\u000f"));
		
		assertEquals("<irc>DALnet's recommended mIRC scripting & bot help channel. <font color=\"#FF0000\">Visit us at <u>www.dalnethelpdesk.com</font></u> for scripting info, forums, and searchable logs/stats <font color=\"#0000FF\">Looking for a script/bot/addon?</font> <b><u>mircscripts.org</b></u> <font color=\"#FF0000\">or</font> <b><u>mirc.net</b></u> <font color=\"#0000FF\"> Writing your own?</font><font color=\"#FF0000\"> Ask <b>here.</b></font><font color=\"#FF0000\"> </font> - <font color=\"#0000FF\">m</font><font color=\"#FF0000\">IR</font><font color=\"#FFFF00\">Casdsaa</font>asdasd v7.14 has been released</irc>",
				ColorFormatter.irc_to_html("DALnet's recommended mIRC scripting & bot help channel. \u00034Visit us at \u001fwww.dalnethelpdesk.com\u000f for \u0003scripting info, forums, and searchable logs/stats \u000312Looking for a script/bot/addon?\u000f \u0002\u001fmircscripts.org\u000f \u00034or\u000f \u0002\u001fmirc.net\u000f \u000312 Writing your own?\u0003\u00034 Ask \u0002here.\u0002 \u000f - \u000312m\u00034IR\u00038Casdsaa\u0003asdasd\u000f v7.14 has been released"));
		
		assertEquals("<irc><b>irccloud:</b> <font color=\"#FFA500\">master</font> <font color=\"#008000\">James Wheare</font> * <b>87ebfc3</b> (1 files in 1 dirs): hidden_host_set formatting - http://bit.ly/bBsyTM</irc>",
				ColorFormatter.irc_to_html("\u0002irccloud:\u0002 \u000307master\u0003 \u000303James Wheare\u0003 * \u000287ebfc3\u0002 (1 files in 1 dirs): hidden_host_set formatting - http://bit.ly/bBsyTM"));
		
		assertEquals("<irc><u>Stuff.Stuff.123.123.-WOOT</u></irc>",
				ColorFormatter.irc_to_html("\u001fStuff.Stuff.123.123.-WOOT\u001f"));
		
		assertEquals("<irc><font color=\"#00FF00\">http://www.google.com/intl/en/about.html</font> asdsa http://www.google.com/intl/en/about.html asdasd</irc>",
				ColorFormatter.irc_to_html("\u00039http://www.google.com/intl/en/about.html\u0003 asdsa http://www.google.com/intl/en/about.html asdasd"));
		
		assertEquals("<irc><font color=\"#00FF00\">http://www.google.com/intl/en/about.html</font>  asdsa  http://www.google.com/intl/en/about.html  asdasd</irc>",
				ColorFormatter.irc_to_html("\u00039http://www.google.com/intl/en/about.html\u0003  asdsa  http://www.google.com/intl/en/about.html  asdasd"));
		
		assertEquals("<irc><u>http://www.google.com/intl/en/about.html</u>  asda  http://www.google.com/intl/en/about.html  asdasd</irc>",
				ColorFormatter.irc_to_html("\u001fhttp://www.google.com/intl/en/about.html\u001f  asda  http://www.google.com/intl/en/about.html  asdasd"));
		
		assertEquals("<irc><b>http://www.google.com/intl/en/about.html</b></irc>",
				ColorFormatter.irc_to_html("\u0002http://www.google.com/intl/en/about.html\u0002"));
		
		assertEquals("<irc><font color=\"#800080\">Stuff.Stuff.123.123.Stuff.Stuff.12345.1234.Stuff-TEST</font></irc>",
				ColorFormatter.irc_to_html("\u00036Stuff.Stuff.123.123.Stuff.Stuff.12345.1234.Stuff-TEST\u0003"));
		
		assertEquals("<irc> <b><_bg000000><font color=\"#FF0000\"> [</font><font color=\"#FFA500\">Test  Title</font><font color=\"#FF0000\">]</font></_bg000000>  blah.hah.hah.and.a.bottle.123.of.123456.0.RUM <_bg000000><font color=\"#FF0000\">[</font><font color=\"#FFA500\">hi/there</font><font color=\"#FF0000\">]</font></_bg000000></b> </irc>",
				ColorFormatter.irc_to_html(" \u0002\u00034,1 [\u000307Test  Title\u00034]\u0003 \u0000blah.hah.hah.and.a.bottle.123.of.123456.0.RUM \u00034,1[\u000307hi/there\u00034]\u000f "));
		
		assertEquals("<irc><_bg000000><font color=\"#FF0000\">h</font></_bg000000><_bg000080><font color=\"#FF0000\">#</font></_bg000080><_bg008000><font color=\"#FF0000\">l</font></_bg008000><_bgFF0000><font color=\"#FF0000\">l</font></_bgFF0000><_bg800000><font color=\"#FF0000\">o</font></_bg800000></irc>",
				ColorFormatter.irc_to_html("\u00034,1h\u000f\u00034,2#\u000f\u00034,3l\u000f\u00034,4l\u000f\u00034,5o\u000f"));
		
		assertEquals("<irc><b><font color=\"#0000FF\">http://www.site.com/</font></b></irc>",
				ColorFormatter.irc_to_html("\u0002\u000312http://www.site.com/\u000f"));
		
		assertEquals("<irc>i was last seen \\ \\<font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FFA500\">piss^</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FFA500\">._</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#808080\"> '--' </font><font color=\"#0000FF\">'-.\\__/ </font><font color=\"#808080\">_</font><font color=\"#0000FF\">l</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FF0000\">`\\</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FF0000\">\\</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FF00FF\">||</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FFA500\">/</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FF0000\"><b>piss</b></font><font color=\"#FF0000\"></font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FFA500\">^`</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#00FF00\">)</font><font color=\"#808080\">\\</font><font color=\"#C0C0C0\">((((</font><font color=\"#FFA500\">\\</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FFA500\">.</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FFFF00\"> :;;,,</font><font color=\"#FF0000\">'-._</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#FF0000\">\\</font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"> </font><font color=\"#000000\"></font></irc>",
				ColorFormatter.irc_to_html("i was last seen \\ \\\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00037piss^\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00037._\u00031 \u00031 \u00031 \u000314 '--' \u000312'-.\\__/ \u000314_\u000312l\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00034`\\\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00034\\\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u000313||\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00037/\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00034\u0002piss\u0002\u00031 \u00031 \u00037^`\u00031 \u00031 \u00031 \u00031 \u00039)\u000314\\\u000315((((\u00037\\\u00031 \u00031 \u00031 \u00037.\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00038 :;;,,\u00034'-._\u00031 \u00031 \u00034\\\u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031 \u00031"));
		
		assertEquals("<irc><font color=\"#55ee22\">some text in 55ee22 rgb</font></irc>",
				ColorFormatter.irc_to_html("\u00045e2some text in 55ee22 rgb\u0004"));
		
		assertEquals("<irc><font color=\"#55ee22\">some text in 55ee22 rgb</font></irc>",
				ColorFormatter.irc_to_html("\u000455ee22some text in 55ee22 rgb\u0004"));
		
		assertEquals("<irc><font color=\"#55ee22\">,some text in 55ee22 rgb</font></irc>",
				ColorFormatter.irc_to_html("\u000455ee22,some text in 55ee22 rgb\u0004"));
		
		assertEquals("<irc><_bgaaaaaa><font color=\"#55ee22\">some text in 55ee22 rgb on aaaaaa bg</font></_bgaaaaaa></irc>",
				ColorFormatter.irc_to_html("\u00045e2,aaasome text in 55ee22 rgb on aaaaaa bg\u0004"));
		
		assertEquals("<irc><_bgaaaaaa><font color=\"#55ee22\">some text in 55ee22 rgb on aaaaaa bg</font></_bgaaaaaa></irc>",
				ColorFormatter.irc_to_html("\u000455ee22,aaaaaasome text in 55ee22 rgb on aaaaaa bg\u0004"));
		
		assertEquals("<irc><_bgaaaaaa><font color=\"#55ee22\">,some text in 55ee22 rgb on aaaaaa bg</font></_bgaaaaaa></irc>",
				ColorFormatter.irc_to_html("\u000455ee22,aaaaaa,some text in 55ee22 rgb on aaaaaa bg\u0004"));
		
		assertEquals("<irc><_bgaaaaaa><font color=\"#55ee22\">,some text in 55ee22 rgb on aaaaaa bg</font></_bgaaaaaa><b> Bold</b></irc>",
				ColorFormatter.irc_to_html("\u000455ee22,aaaaaa,some text in 55ee22 rgb on aaaaaa bg\u0004\u0002 Bold\u0002"));
		
		assertEquals("<irc><_bgaaaaaa><font color=\"#55ee22\">,some text in 55ee22 rgb on aaaaaa bg</font></_bgaaaaaa> </irc>",
				ColorFormatter.irc_to_html("\u000455ee22,aaaaaa,some text in 55ee22 rgb on aaaaaa bg\u0004\u0000"));
		
		assertEquals("<irc><_bgaaaaaa><font color=\"#55ee22\">,some text in 55ee22 rgb on aaaaaa bg</font></_bgaaaaaa><font color=\"#FF0000\"> Red</font></irc>",
				ColorFormatter.irc_to_html("\u000455ee22,aaaaaa,some text in 55ee22 rgb on aaaaaa bg\u0004\u00034 Red\u0003"));

        assertEquals("<irc><b>Bold</b></irc>",
                ColorFormatter.irc_to_html("\u0002Bold\u0002"));

        assertEquals("<irc>\u0010Italics\u0010</irc>",
                ColorFormatter.irc_to_html("\u0010Italics\u0010"));

        assertEquals("<irc><i>Italics</i></irc>",
                ColorFormatter.irc_to_html("\u0016Italics\u0016"));

        assertEquals("<irc><i>Italics</i></irc>",
                ColorFormatter.irc_to_html("\u001DItalics\u001D"));

        assertEquals("<irc>Found: <font color=\"#800080\">biomesoplenty</font>(dev) (Version <font color=\"#800000\">2.1.0.950</font>) - Last updated Fri Aug 01 23:54:18 BST 2014</irc>",
                ColorFormatter.irc_to_html("Found: \u000F\u000306biomesoplenty\u0003\u0003\u0003(dev) (Version \u000F\u0003052.1.0.950\u0003) - Last updated Fri Aug 01 23:54:18 BST 2014"));

        assertEquals("<irc><b>Test <font color=\"#FF0000\">Test</b></font><font color=\"#FF0000\"> Test</font></irc>",
                ColorFormatter.irc_to_html("\u0002Test \u00034Test\u0002 Test"));

        assertEquals("<irc><b><_bgFFFF00><font color=\"#000000\"> <u>/!\\</u></font></_bgFFFF00></b><font color=\"#000000\"><_bgFFFF00><b> /// </font></_bgFFFF00><_bgFFFF00><font color=\"#FF0000\">[WARNING: NSFW/NSFL]</font></_bgFFFF00><_bgFFFF00><font color=\"#000000\"> \\\\\\ <u>/!\\</u></font></_bgFFFF00></b><font color=\"#000000\"><_bgFFFF00><b> </font></_bgFFFF00></b><_bg800000><font color=\"#C0C0C0\"> Behold, the grotesquery of The Crocobeetus! </font></_bg800000><_bg800000><font color=\"#00FF00\"><u>https://example.com/</u></font></_bg800000><font color=\"#00FF00\"><_bg800000></font></_bg800000><_bg800000><font color=\"#C0C0C0\"> </font></_bg800000><b><_bgFFFF00><font color=\"#000000\"> <u>/!\\</u></font></_bgFFFF00></b><font color=\"#000000\"><_bgFFFF00><b> /// </font></_bgFFFF00><_bgFFFF00><font color=\"#FF0000\">[WARNING: NSFW/NSFL]</font></_bgFFFF00><_bgFFFF00><font color=\"#000000\"> \\\\\\ <u>/!\\</u></font></_bgFFFF00></b><font color=\"#000000\"><_bgFFFF00><b> </font></_bgFFFF00></b></irc>",
                ColorFormatter.irc_to_html("\u0002\u00031,8 \u001F/!\\\u001F /// \u00034,8[WARNING: NSFW/NSFL]\u00031,8 \\\\\\ \u001F/!\\\u001F \u0003\u0002\u000315,5 Behold, the grotesquery of The Crocobeetus! \u00039,5\u001Fhttps://example.com/\u001F\u000315,5 \u0003\u0002\u00031,8 \u001F/!\\\u001F /// \u00034,8[WARNING: NSFW/NSFL]\u00031,8 \\\\\\ \u001F/!\\\u001F \u0003\u0002"));
    }

    private void checkURL(String input, String expected) {
        Spanned s = ColorFormatter.html_to_spanned(ColorFormatter.irc_to_html(input), true, null);
        ColorFormatter.URLSpanNoUnderline[] spans = s.getSpans(0, s.length(), ColorFormatter.URLSpanNoUnderline.class);
        assertTrue(spans.length > 0);
        assertEquals(expected, spans[0].getURL());
    }

    public void testUrls() {
        checkURL("blah www.isktheguide.com/test/tis.html blah</irc>", "http://www.isktheguide.com/test/tis.html");
        checkURL("blah (www.isktheguide.com/test/tis.html) blah</irc>", "http://www.isktheguide.com/test/tis.html");
        checkURL("http://foo.com/blah_blah</irc>", "http://foo.com/blah_blah");
        checkURL("http://foo.com/blah_blah/</irc>", "http://foo.com/blah_blah/");
        checkURL("(Something like http://foo.com/blah_blah)</irc>", "http://foo.com/blah_blah");
        checkURL("http://foo.com/blah_blah_(wikipedia)</irc>", "http://foo.com/blah_blah_(wikipedia)");
        checkURL("(Something like http://foo.com/blah_blah_(wikipedia))</irc>", "http://foo.com/blah_blah_(wikipedia)");
        checkURL("(Something like http://foo.com/blah_blah_)wikipedia()</irc>", "http://foo.com/blah_blah_)wikipedia(");
        checkURL("(Something like http://foo.com/blah_blah_{wikipedia})</irc>", "http://foo.com/blah_blah_{wikipedia}");
        checkURL("(Something like http://foo.com/blah_blah_[wikipedia])</irc>", "http://foo.com/blah_blah_[wikipedia]");
        checkURL("http://foo.com/blah_blah.</irc>", "http://foo.com/blah_blah");
        checkURL("http://foo.com/blah_blah!</irc>", "http://foo.com/blah_blah");
        checkURL("http://foo.com/!</irc>", "http://foo.com/");
        checkURL("http://lifehacker.com/#!5794512</irc>", "http://lifehacker.com/#!5794512");
        checkURL("http://foo.com!</irc>", "http://foo.com");
        checkURL("http://foo.com/blah_blah/.</irc>", "http://foo.com/blah_blah/");
        checkURL("http://foo.com/blah_blah,</irc>", "http://foo.com/blah_blah");
        checkURL("http://www.example.com/wpstyle/?p=364.</irc>", "http://www.example.com/wpstyle/?p=364");
        checkURL("http://✪df.ws/123</irc>", "http://✪df.ws/123");
        checkURL("http://userid:password@example.com:8080</irc>", "http://userid:password@example.com:8080");
        checkURL("http://www.marinetraffic.com/ais/details/ships/shipid:152182/mmsi:218284000/imo:9343730/vessel:KUALA_LUMPUR_EXPRESS</irc>", "http://www.marinetraffic.com/ais/details/ships/shipid:152182/mmsi:218284000/imo:9343730/vessel:KUALA_LUMPUR_EXPRESS");
        checkURL("http://www.marinetraffic.com:80/ais/details/ships/shipid:152182/mmsi:218284000/imo:9343730/vessel:KUALA_LUMPUR_EXPRESS</irc>", "http://www.marinetraffic.com:80/ais/details/ships/shipid:152182/mmsi:218284000/imo:9343730/vessel:KUALA_LUMPUR_EXPRESS");
        checkURL("http://www.marinetraffic.com:http/ais/details/ships/shipid:152182/mmsi:218284000/imo:9343730/vessel:KUALA_LUMPUR_EXPRESS</irc>", "http://www.marinetraffic.com");
        checkURL("http://www.marinetraffic.com/:http/ais/details/ships/shipid:152182/mmsi:218284000/imo:9343730/vessel:KUALA_LUMPUR_EXPRESS</irc>", "http://www.marinetraffic.com/:http/ais/details/ships/shipid:152182/mmsi:218284000/imo:9343730/vessel:KUALA_LUMPUR_EXPRESS");
        checkURL("http://google.com:80</irc>", "http://google.com:80");
        checkURL("http://google.com/:80</irc>", "http://google.com/:80");
        checkURL("http://google.com:http</irc>", "http://google.com");
        checkURL("http://google.com:80:http</irc>", "http://google.com:80");
        checkURL("http://google.com/:http</irc>", "http://google.com/:http");
        checkURL("http://google.com:http@google.com</irc>", "http://google.com:http@google.com");
        checkURL("http://google.com:http@google.com:80</irc>", "http://google.com:http@google.com:80");
        checkURL("http://google.com:http@google.com/:80</irc>", "http://google.com:http@google.com/:80");
        checkURL("http://google.com:http@google.com:http</irc>", "http://google.com:http@google.com");
        checkURL("http://google.com:http@google.com:80:http</irc>", "http://google.com:http@google.com:80");
        checkURL("http://google.com:http@google.com/:http</irc>", "http://google.com:http@google.com/:http");
        checkURL("http://userid@example.com</irc>", "http://userid@example.com");
        checkURL("http://userid@example.com:8080</irc>", "http://userid@example.com:8080");
        checkURL("http://userid:password@example.com</irc>", "http://userid:password@example.com");
        checkURL("<tag>http://example.com</tag></irc>", "http://example.com");
        checkURL("Just a www.example.com link.</irc>", "http://www.example.com");
        checkURL("http://foo.com/more_(than)_one_(parens)</irc>", "http://foo.com/more_(than)_one_(parens)");
        checkURL("http://foo.com/more_[than]_one_[parens]</irc>", "http://foo.com/more_[than]_one_[parens]");
        checkURL("http://foo.com/more_{than}_one_{parens}</irc>", "http://foo.com/more_{than}_one_{parens}");
        checkURL("http://foo.com/more_(than)_one_[parens]_{test}</irc>", "http://foo.com/more_(than)_one_[parens]_{test}");
        checkURL("http://foo.com/blah_(wikipedia)#cite-1</irc>", "http://foo.com/blah_(wikipedia)#cite-1");
        checkURL("http://foo.com/blah_(wikipedia)_blah#cite-1</irc>", "http://foo.com/blah_(wikipedia)_blah#cite-1");
        checkURL("http://foo.com/unicode_(✪)_in_parens</irc>", "http://foo.com/unicode_(✪)_in_parens");
        checkURL("http://foo.com/(something)?after=parens</irc>", "http://foo.com/(something)?after=parens");
        checkURL("https://github.com/RJ/irccloud/compare/e9f748e760^...a8e784b39</irc>", "https://github.com/RJ/irccloud/compare/e9f748e760^...a8e784b39");
        checkURL("example.com!</irc>", "http://example.com");
        checkURL("example.com?</irc>", "http://example.com");
        checkURL("example.com/?</irc>", "http://example.com/");
        checkURL("example.com/?ref=123</irc>", "http://example.com/?ref=123");
        checkURL("example.com/#ref=123</irc>", "http://example.com/#ref=123");
        checkURL("example.com?ref=123</irc>", "http://example.com?ref=123");
        checkURL("www.youtube.com/watch?v=kK42LZqO0wA</irc>", "http://www.youtube.com/watch?v=kK42LZqO0wA");
        checkURL("www.youtube.com?v=kK42LZqO0wA</irc>", "http://www.youtube.com?v=kK42LZqO0wA");
        checkURL("youtube.com/watch?v=kK42LZqO0wA</irc>", "http://youtube.com/watch?v=kK42LZqO0wA");
        checkURL("youtube.com?v=kK42LZqO0wA</irc>", "http://youtube.com?v=kK42LZqO0wA");
        checkURL("youtube.com/?v=kK42LZqO0wA</irc>", "http://youtube.com/?v=kK42LZqO0wA");
        checkURL("www.youtube.com/?v=kK42LZqO0wA</irc>", "http://www.youtube.com/?v=kK42LZqO0wA");
        checkURL("example.com#ref=123</irc>", "http://example.com#ref=123");
        checkURL("http://example.com?</irc>", "http://example.com");
        checkURL("http://example.com/?</irc>", "http://example.com/");
        checkURL("http://example.com?ref=123</irc>", "http://example.com?ref=123");
        checkURL("http://example.com#ref=123</irc>", "http://example.com#ref=123");
        checkURL("http://example.com/?ref=123</irc>", "http://example.com/?ref=123");
        checkURL("http://example.com/#ref=123</irc>", "http://example.com/#ref=123");
        checkURL("google.com</irc>", "http://google.com");
        checkURL("'google.com'</irc>", "http://google.com");
        checkURL("\"google.com\"</irc>", "http://google.com");
        checkURL("(google.com)</irc>", "http://google.com");
        checkURL("[google.com]</irc>", "http://google.com");
        checkURL("google.com/test</irc>", "http://google.com/test");
        checkURL("'google.com/test'</irc>", "http://google.com/test");
        checkURL("\"google.com/test\"</irc>", "http://google.com/test");
        checkURL("(google.com/test)</irc>", "http://google.com/test");
        checkURL("[google.com/test]</irc>", "http://google.com/test");
        checkURL("http://google.com</irc>", "http://google.com");
        checkURL("'http://google.com'</irc>", "http://google.com");
        checkURL("\"http://google.com\"</irc>", "http://google.com");
        checkURL("(http://google.com)</irc>", "http://google.com");
        checkURL("[http://google.com]</irc>", "http://google.com");
        checkURL("http://google.com/test</irc>", "http://google.com/test");
        checkURL("'http://google.com/test'</irc>", "http://google.com/test");
        checkURL("\"http://google.com/test\"</irc>", "http://google.com/test");
        checkURL("(http://google.com/test)</irc>", "http://google.com/test");
        checkURL("[http://google.com/test]</irc>", "http://google.com/test");
        checkURL("example.com?hello=mum</irc>", "http://example.com?hello=mum");
        checkURL("example.com#hashbang</irc>", "http://example.com#hashbang");
        checkURL("lifehacker.com/#!5794512</irc>", "http://lifehacker.com/#!5794512");
        checkURL("example.com:8080</irc>", "http://example.com:8080");
        checkURL("james.dev.last.fm</irc>", "http://james.dev.last.fm");
        checkURL("foo.com/bar.txt</irc>", "http://foo.com/bar.txt");
        checkURL("\u0002\u000312http://something.com\u0003\u0002</irc>", "http://something.com");
        checkURL("irc.irccloud.com</irc>", "irc://irc.irccloud.com");
        checkURL("Http://google.com</irc>", "http://google.com");
        checkURL("HTTP://GOOGLE.COM</irc>", "http://GOOGLE.COM");
        checkURL("GOOGLE.COM</irc>", "http://GOOGLE.COM");
    }

    public void testImages() {
        checkURL("https://www.google.co.uk/images/srpr/logo11w.png</irc>", "irccloud-images://www.google.co.uk/images/srpr/logo11w.png");
        checkURL("https://www.google.co.uk/images/srpr/logo11w.jpg</irc>", "irccloud-images://www.google.co.uk/images/srpr/logo11w.jpg");
        checkURL("https://www.google.co.uk/images/srpr/logo11w.jpeg</irc>", "irccloud-images://www.google.co.uk/images/srpr/logo11w.jpeg");
        checkURL("https://www.google.co.uk/images/srpr/logo11w.gif</irc>", "irccloud-images://www.google.co.uk/images/srpr/logo11w.gif");
        checkURL("https://www.google.co.uk/images/srpr/logo11w.bmp</irc>", "irccloud-images://www.google.co.uk/images/srpr/logo11w.bmp");
        checkURL("http://f.cl.ly/items/3F0Q2L0f0V2F2z342c16/Screen%20Shot%202013-11-19%20at%2020.59.40.png</irc>", "irccloud-image://f.cl.ly/items/3F0Q2L0f0V2F2z342c16/Screen%20Shot%202013-11-19%20at%2020.59.40.png");
        checkURL("http://d.pr/i/JONs</irc>", "irccloud-image://d.pr/i/JONs");
        checkURL("http://droplr.com/i/JONs</irc>", "irccloud-image://droplr.com/i/JONs");
        checkURL("http://cloud-4.steampowered.com/ugc/633039016643356912/4928C2DE66D7946F5B2814A1B5483928650A4170/</irc>", "irccloud-image://cloud-4.steampowered.com/ugc/633039016643356912/4928C2DE66D7946F5B2814A1B5483928650A4170/");
        checkURL("http://www.flickr.com/photos/jwheare/12015278916/</irc>", "irccloud-image://www.flickr.com/photos/jwheare/12015278916/");
        checkURL("http://instagram.com/p/hZiTV3Tbl4/</irc>", "irccloud-image://instagram.com/p/hZiTV3Tbl4/");
        checkURL("http://instagr.am/p/hZiTV3Tbl4/</irc>", "irccloud-image://instagr.am/p/hZiTV3Tbl4/");
        checkURL("http://imgur.com/gJUafck</irc>", "irccloud-image://imgur.com/gJUafck");
    }
}
