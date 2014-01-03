/**
 * Copyright (c) 2012-2013, JCabi.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the jcabi.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jcabi.github;

import javax.json.Json;
import javax.json.JsonObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assume;
import org.junit.Test;

/**
 * Integration case for {@link com.jcabi.github.Repos}.
 * @author Gena Svarovski (g.svarovski@gmail.com)
 * @version $Id$
 * @since 0.6
 * @checkstyle ClassDataAbstractionCoupling (500 lines)
 */
public final class RtReposITCase {
    /**
     * RtRepos can create a new repo.
     * @throws Exception If some problem inside
     */
    @Test
    public void createsNewRepo() throws Exception {
        final String name = "test-repo";
        final String description = "The description";
        final Repos repos = RtReposITCase.repos();
        final JsonObject createRequest = createRequest(name, description);
        final Repo repo = repos.create(createRequest);
        MatcherAssert.assertThat(
            repo,
            Matchers.notNullValue()
        );
        MatcherAssert.assertThat(
            repo.coordinates().repo(),
            Matchers.equalTo(name)
        );
    }

    /**
     * Create and return JsonObject to test.
     * @param name Repo name
     * @param description Repo description
     * @return JsonObject
     * @throws Exception If some problem inside
     */
    private static JsonObject createRequest(
        final String name, final String description) throws Exception {
        return Json.createObjectBuilder()
            .add("name", name)
            .add("description", description)
            .build();
    }

    /**
     * Create and return repo to test.
     * @return Repo
     * @throws Exception If some problem inside
     */
    private static Repos repos() throws Exception {
        final String key = System.getProperty("failsafe.github.key");
        Assume.assumeThat(key, Matchers.notNullValue());
        final Github github = new RtGithub(key);
        return github.repos();
    }

}
