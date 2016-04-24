package org.udger.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UdgerParserTest {

    private UdgerParser parser;

    @Before
    public void initialize() {
        parser = new UdgerParser("udgerdb_v3.dat");
    }

    @After
    public void close() throws IOException {
        parser.close();
    }

    @Test
    public void testUaString1() throws SQLException {
        String uaQuery = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534+ (KHTML, like Gecko) BingPreview/1.0b";
        UdgerUaQueryResult qr = parser.parseUa(uaQuery);
        assertEquals(qr.getUserAgent().getUa(), "BingPreview/1.0b");
        assertEquals(qr.getOs().getOs(), "Windows 7");
        assertEquals(qr.getUserAgent().getUaFamily(), "bingbot");
    }

    @Test
    public void testIp() throws SQLException, UnknownHostException {
        String ipQuery = "108.61.199.93";
        UdgerIpQueryResult qr = parser.parseIp(ipQuery);
        assertEquals(qr.getIp().getIpClassificationCode(), "crawler");
    }

}