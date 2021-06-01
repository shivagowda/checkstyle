////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2021 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package com.puppycrawl.tools.checkstyle.checks.coding;

import static com.puppycrawl.tools.checkstyle.checks.coding.VariableDeclarationUsageDistanceCheck.MSG_KEY;
import static com.puppycrawl.tools.checkstyle.checks.coding.VariableDeclarationUsageDistanceCheck.MSG_KEY_EXT;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

public class VariableDeclarationUsageDistanceCheckTest extends
        AbstractModuleTestSupport {

    @Override
    protected String getPackageLocation() {
        return "com/puppycrawl/tools/checkstyle/checks/coding/variabledeclarationusagedistance";
    }

    @Test
    public void testGeneralLogic() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        checkConfig.addAttribute("allowedDistance", "1");
        checkConfig.addAttribute("ignoreVariablePattern", "");
        checkConfig.addAttribute("validateBetweenScopes", "true");
        checkConfig.addAttribute("ignoreFinal", "false");
        final String[] expected = {
            "37:9: " + getCheckMessage(MSG_KEY, "a", 2, 1),
            "45:9: " + getCheckMessage(MSG_KEY, "temp", 2, 1),
            "51:9: " + getCheckMessage(MSG_KEY, "temp", 2, 1),
            "64:9: " + getCheckMessage(MSG_KEY, "count", 2, 1),
            "78:9: " + getCheckMessage(MSG_KEY, "count", 4, 1),
            "103:9: " + getCheckMessage(MSG_KEY, "arg", 2, 1),
            "151:9: " + getCheckMessage(MSG_KEY, "m", 3, 1),
            "152:9: " + getCheckMessage(MSG_KEY, "n", 2, 1),
            "191:9: " + getCheckMessage(MSG_KEY, "result", 2, 1),
            "226:9: " + getCheckMessage(MSG_KEY, "t", 5, 1),
            "229:9: " + getCheckMessage(MSG_KEY, "c", 3, 1),
            "230:9: " + getCheckMessage(MSG_KEY, "d2", 3, 1),
            "267:9: " + getCheckMessage(MSG_KEY, "sel", 2, 1),
            "268:9: " + getCheckMessage(MSG_KEY, "model", 2, 1),
            "294:9: " + getCheckMessage(MSG_KEY, "sw", 2, 1),
            "307:9: " + getCheckMessage(MSG_KEY, "wh", 2, 1),
            "350:9: " + getCheckMessage(MSG_KEY, "green", 2, 1),
            "351:9: " + getCheckMessage(MSG_KEY, "blue", 3, 1),
            "374:9: " + getCheckMessage(MSG_KEY, "intervalMs", 2, 1),
            "461:9: " + getCheckMessage(MSG_KEY, "aOpt", 3, 1),
            "462:9: " + getCheckMessage(MSG_KEY, "bOpt", 2, 1),
            "478:9: " + getCheckMessage(MSG_KEY, "l1", 3, 1),
            "478:9: " + getCheckMessage(MSG_KEY, "l2", 2, 1),
            "486:9: " + getCheckMessage(MSG_KEY, "myOption", 7, 1),
            "498:9: " + getCheckMessage(MSG_KEY, "myOption", 6, 1),
            "511:9: " + getCheckMessage(MSG_KEY, "count", 4, 1),
            "512:15: " + getCheckMessage(MSG_KEY, "files", 2, 1),
            "547:13: " + getCheckMessage(MSG_KEY, "id", 2, 1),
            "549:13: " + getCheckMessage(MSG_KEY, "parentId", 3, 1),
            "898:9: " + getCheckMessage(MSG_KEY, "a", 4, 1),
            "908:9: " + getCheckMessage(MSG_KEY, "a", 4, 1),
            "974:9: " + getCheckMessage(MSG_KEY, "a", 4, 1),
            "985:9: " + getCheckMessage(MSG_KEY, "a", 2, 1),
            "996:9: " + getCheckMessage(MSG_KEY, "a", 3, 1),
            "1031:9: " + getCheckMessage(MSG_KEY, "c", 3, 1),
            "1061:9: " + getCheckMessage(MSG_KEY, "a", 4, 1),
        };
        verify(checkConfig, getPath("InputVariableDeclarationUsageDistanceGeneral.java"), expected);
    }

    @Test
    public void testDistance() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        checkConfig.addAttribute("allowedDistance", "3");
        checkConfig.addAttribute("ignoreVariablePattern", "");
        checkConfig.addAttribute("validateBetweenScopes", "true");
        checkConfig.addAttribute("ignoreFinal", "false");
        final String[] expected = {
            "78:9: " + getCheckMessage(MSG_KEY, "count", 4, 3),
            "226:9: " + getCheckMessage(MSG_KEY, "t", 5, 3),
            "486:9: " + getCheckMessage(MSG_KEY, "myOption", 7, 3),
            "498:9: " + getCheckMessage(MSG_KEY, "myOption", 6, 3),
            "511:9: " + getCheckMessage(MSG_KEY, "count", 4, 3),
            "898:9: " + getCheckMessage(MSG_KEY, "a", 4, 3),
            "908:9: " + getCheckMessage(MSG_KEY, "a", 4, 3),
            "974:9: " + getCheckMessage(MSG_KEY, "a", 4, 3),
            "1061:9: " + getCheckMessage(MSG_KEY, "a", 4, 3),
        };
        verify(checkConfig, getPath("InputVariableDeclarationUsageDistance.java"), expected);
    }

    @Test
    public void testVariableRegExp() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        checkConfig.addAttribute("allowedDistance", "1");
        checkConfig.addAttribute("ignoreVariablePattern",
                "a|b|c|d|block|dist|t|m");
        checkConfig.addAttribute("validateBetweenScopes", "true");
        checkConfig.addAttribute("ignoreFinal", "false");
        final String[] expected = {
            "45:9: " + getCheckMessage(MSG_KEY, "temp", 2, 1),
            "51:9: " + getCheckMessage(MSG_KEY, "temp", 2, 1),
            "64:9: " + getCheckMessage(MSG_KEY, "count", 2, 1),
            "78:9: " + getCheckMessage(MSG_KEY, "count", 4, 1),
            "103:9: " + getCheckMessage(MSG_KEY, "arg", 2, 1),
            "152:9: " + getCheckMessage(MSG_KEY, "n", 2, 1),
            "191:9: " + getCheckMessage(MSG_KEY, "result", 2, 1),
            "230:9: " + getCheckMessage(MSG_KEY, "d2", 3, 1),
            "267:9: " + getCheckMessage(MSG_KEY, "sel", 2, 1),
            "268:9: " + getCheckMessage(MSG_KEY, "model", 2, 1),
            "294:9: " + getCheckMessage(MSG_KEY, "sw", 2, 1),
            "307:9: " + getCheckMessage(MSG_KEY, "wh", 2, 1),
            "350:9: " + getCheckMessage(MSG_KEY, "green", 2, 1),
            "351:9: " + getCheckMessage(MSG_KEY, "blue", 3, 1),
            "374:9: " + getCheckMessage(MSG_KEY, "intervalMs", 2, 1),
            "461:9: " + getCheckMessage(MSG_KEY, "aOpt", 3, 1),
            "462:9: " + getCheckMessage(MSG_KEY, "bOpt", 2, 1),
            "478:9: " + getCheckMessage(MSG_KEY, "l1", 3, 1),
            "478:9: " + getCheckMessage(MSG_KEY, "l2", 2, 1),
            "486:9: " + getCheckMessage(MSG_KEY, "myOption", 7, 1),
            "498:9: " + getCheckMessage(MSG_KEY, "myOption", 6, 1),
            "511:9: " + getCheckMessage(MSG_KEY, "count", 4, 1),
            "512:15: " + getCheckMessage(MSG_KEY, "files", 2, 1),
            "547:13: " + getCheckMessage(MSG_KEY, "id", 2, 1),
            "549:13: " + getCheckMessage(MSG_KEY, "parentId", 3, 1),
        };
        verify(checkConfig, getPath("InputVariableDeclarationUsageDistanceRegExp.java"), expected);
    }

    @Test
    public void testValidateBetweenScopesOption() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        checkConfig.addAttribute("allowedDistance", "1");
        checkConfig.addAttribute("ignoreVariablePattern", "");
        checkConfig.addAttribute("validateBetweenScopes", "false");
        checkConfig.addAttribute("ignoreFinal", "false");
        final String[] expected = {
            "37:9: " + getCheckMessage(MSG_KEY, "a", 2, 1),
            "45:9: " + getCheckMessage(MSG_KEY, "temp", 2, 1),
            "51:9: " + getCheckMessage(MSG_KEY, "temp", 2, 1),
            "78:9: " + getCheckMessage(MSG_KEY, "count", 4, 1),
            "103:9: " + getCheckMessage(MSG_KEY, "arg", 2, 1),
            "226:9: " + getCheckMessage(MSG_KEY, "t", 5, 1),
            "229:9: " + getCheckMessage(MSG_KEY, "c", 3, 1),
            "230:9: " + getCheckMessage(MSG_KEY, "d2", 3, 1),
            "307:9: " + getCheckMessage(MSG_KEY, "wh", 2, 1),
            "350:9: " + getCheckMessage(MSG_KEY, "green", 2, 1),
            "351:9: " + getCheckMessage(MSG_KEY, "blue", 3, 1),
            "374:9: " + getCheckMessage(MSG_KEY, "intervalMs", 2, 1),
            "461:9: " + getCheckMessage(MSG_KEY, "aOpt", 3, 1),
            "462:9: " + getCheckMessage(MSG_KEY, "bOpt", 2, 1),
            "478:9: " + getCheckMessage(MSG_KEY, "l1", 3, 1),
            "478:9: " + getCheckMessage(MSG_KEY, "l2", 2, 1),
            "486:9: " + getCheckMessage(MSG_KEY, "myOption", 7, 1),
            "498:9: " + getCheckMessage(MSG_KEY, "myOption", 6, 1),
            "512:15: " + getCheckMessage(MSG_KEY, "files", 2, 1),
            "547:13: " + getCheckMessage(MSG_KEY, "id", 2, 1),
            "549:13: " + getCheckMessage(MSG_KEY, "parentId", 4, 1),
            "985:9: " + getCheckMessage(MSG_KEY, "a", 2, 1),
            "1031:9: " + getCheckMessage(MSG_KEY, "c", 4, 1),
            "1061:9: " + getCheckMessage(MSG_KEY, "a", 4, 1),
        };
        verify(checkConfig, getPath("InputVariableDeclarationUsageDistanceScopes.java"), expected);
    }

    @Test
    public void testIgnoreFinalOption() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        checkConfig.addAttribute("allowedDistance", "1");
        checkConfig.addAttribute("ignoreVariablePattern", "");
        checkConfig.addAttribute("validateBetweenScopes", "true");
        checkConfig.addAttribute("ignoreFinal", "true");
        final String[] expected = {
            "37:9: " + getCheckMessage(MSG_KEY_EXT, "a", 2, 1),
            "45:9: " + getCheckMessage(MSG_KEY_EXT, "temp", 2, 1),
            "51:9: " + getCheckMessage(MSG_KEY_EXT, "temp", 2, 1),
            "64:9: " + getCheckMessage(MSG_KEY_EXT, "count", 2, 1),
            "78:9: " + getCheckMessage(MSG_KEY_EXT, "count", 4, 1),
            "103:9: " + getCheckMessage(MSG_KEY_EXT, "arg", 2, 1),
            "151:9: " + getCheckMessage(MSG_KEY_EXT, "m", 3, 1),
            "152:9: " + getCheckMessage(MSG_KEY_EXT, "n", 2, 1),
            "191:9: " + getCheckMessage(MSG_KEY_EXT, "result", 2, 1),
            "226:9: " + getCheckMessage(MSG_KEY_EXT, "t", 5, 1),
            "229:9: " + getCheckMessage(MSG_KEY_EXT, "c", 3, 1),
            "230:9: " + getCheckMessage(MSG_KEY_EXT, "d2", 3, 1),
            "267:9: " + getCheckMessage(MSG_KEY_EXT, "sel", 2, 1),
            "268:9: " + getCheckMessage(MSG_KEY_EXT, "model", 2, 1),
            "294:9: " + getCheckMessage(MSG_KEY_EXT, "sw", 2, 1),
            "307:9: " + getCheckMessage(MSG_KEY_EXT, "wh", 2, 1),
            "350:9: " + getCheckMessage(MSG_KEY_EXT, "green", 2, 1),
            "351:9: " + getCheckMessage(MSG_KEY_EXT, "blue", 3, 1),
            "461:9: " + getCheckMessage(MSG_KEY_EXT, "aOpt", 3, 1),
            "462:9: " + getCheckMessage(MSG_KEY_EXT, "bOpt", 2, 1),
            "478:9: " + getCheckMessage(MSG_KEY_EXT, "l1", 3, 1),
            "478:9: " + getCheckMessage(MSG_KEY_EXT, "l2", 2, 1),
            "486:9: " + getCheckMessage(MSG_KEY_EXT, "myOption", 7, 1),
            "498:9: " + getCheckMessage(MSG_KEY_EXT, "myOption", 6, 1),
            "511:9: " + getCheckMessage(MSG_KEY_EXT, "count", 4, 1),
            "512:15: " + getCheckMessage(MSG_KEY_EXT, "files", 2, 1),
            "547:13: " + getCheckMessage(MSG_KEY_EXT, "id", 2, 1),
            "549:13: " + getCheckMessage(MSG_KEY_EXT, "parentId", 3, 1),
            "898:9: " + getCheckMessage(MSG_KEY_EXT, "a", 4, 1),
            "908:9: " + getCheckMessage(MSG_KEY_EXT, "a", 4, 1),
            "974:9: " + getCheckMessage(MSG_KEY_EXT, "a", 4, 1),
            "996:9: " + getCheckMessage(MSG_KEY_EXT, "a", 3, 1),
            "1031:9: " + getCheckMessage(MSG_KEY_EXT, "c", 3, 1),
            "1061:9: " + getCheckMessage(MSG_KEY_EXT, "a", 4, 1),
        };
        verify(checkConfig, getPath("InputVariableDeclarationUsageDistanceFinal.java"), expected);
    }

    @Test
    public void testTokensNotNull() {
        final VariableDeclarationUsageDistanceCheck check =
            new VariableDeclarationUsageDistanceCheck();
        assertNotNull(check.getAcceptableTokens(), "Acceptable tokens should not be null");
        assertNotNull(check.getDefaultTokens(), "Default tokens should not be null");
        assertNotNull(check.getRequiredTokens(), "Required tokens should not be null");
    }

    @Test
    public void testDefaultConfiguration() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        final String[] expected = {
            "74:9: " + getCheckMessage(MSG_KEY_EXT, "count", 4, 3),
            "222:9: " + getCheckMessage(MSG_KEY_EXT, "t", 5, 3),
            "482:9: " + getCheckMessage(MSG_KEY_EXT, "myOption", 7, 3),
            "494:9: " + getCheckMessage(MSG_KEY_EXT, "myOption", 6, 3),
            "545:13: " + getCheckMessage(MSG_KEY_EXT, "parentId", 4, 3),
            "1027:9: " + getCheckMessage(MSG_KEY_EXT, "c", 4, 3),
            "1057:9: " + getCheckMessage(MSG_KEY_EXT, "a", 4, 3),
        };

        verify(checkConfig, getPath("InputVariableDeclarationUsageDistanceDefault.java"), expected);
    }

    @Test
    public void testAnonymousClass() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        final String[] expected = {
            "11:9: " + getCheckMessage(MSG_KEY_EXT, "prefs", 4, 3),
        };

        verify(checkConfig, getPath("InputVariableDeclarationUsageDistanceAnonymous.java"),
                expected);
    }

    @Test
    public void testLabels() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;

        verify(checkConfig, getPath("InputVariableDeclarationUsageDistanceLabels.java"), expected);
    }

    @Test
    public void testVariableDeclarationUsageDistanceSwitchExpressions() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(VariableDeclarationUsageDistanceCheck.class);
        checkConfig.addAttribute("allowedDistance", "1");
        checkConfig.addAttribute("ignoreVariablePattern", "");
        checkConfig.addAttribute("validateBetweenScopes", "true");
        checkConfig.addAttribute("ignoreFinal", "false");

        final int maxDistance = 1;
        final String[] expected = {
            "32:17: " + getCheckMessage(MSG_KEY, "arg", 2, maxDistance),
            "77:17: " + getCheckMessage(MSG_KEY, "m", 3, maxDistance),
            "78:17: " + getCheckMessage(MSG_KEY, "n", 2, maxDistance),
            "110:17: " + getCheckMessage(MSG_KEY, "arg", 2, maxDistance),
            "152:17: " + getCheckMessage(MSG_KEY, "m", 3, maxDistance),
            "153:17: " + getCheckMessage(MSG_KEY, "n", 2, maxDistance),
            "174:17: " + getCheckMessage(MSG_KEY, "count", 3, maxDistance),
            "194:17: " + getCheckMessage(MSG_KEY, "count", 3, maxDistance),
            };

        final String filename = "InputVariableDeclarationUsageDistanceCheckSwitchExpressions.java";
        verify(checkConfig, getNonCompilablePath(filename), expected);
    }

}
