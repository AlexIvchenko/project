package com.github.netcracker2017team.project.restapi;

import com.github.netcracker2017team.project.restapi.api.BasicAuthTokenSupplier;
import com.github.netcracker2017team.project.restapi.api.RootApi;
import com.github.netcracker2017team.project.restapi.impl.RestTemplatesImpl;
import com.github.netcracker2017team.project.restapi.impl.RootApiImpl;
import com.github.netcracker2017team.project.restapi.impl.UserRestApiImpl;
import com.github.netcracker2017team.project.restapi.impl.uri.UriBuilderImpl;

/**
 * @author Alex Ivchenko
 */
public class RestApi {
    private RestApi() {

    }

    public static SchemaStageBuilder builder() {
        return new RestApiBuilder();
    }

    public static class RestApiBuilder implements SchemaStageBuilder, DomainStageBuilder, PortStageBuilder, PathStageBuilder, TokenStageBuilder {
        private Schema schema;
        private String domain;
        private String port;
        private String path;
        private BasicAuthTokenSupplier tokenSupplier;

        @Override
        public DomainStageBuilder schema(Schema schema) {
            this.schema = schema;
            return this;
        }

        @Override
        public PortStageBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        @Override
        public PathStageBuilder port(String port) {
            this.port = port;
            return this;
        }

        @Override
        public TokenStageBuilder path(String path) {
            this.path = path;
            return this;
        }

        @Override
        public RootApi tokens(BasicAuthTokenSupplier tokenSupplier) {
            this.tokenSupplier = tokenSupplier;
            return build();
        }

        private RootApi build() {
            String uri = String.format("%s://%s:%s/%s",
                    schema.toString(), domain, port, path);
            return new RootApiImpl(new UserRestApiImpl(new RestTemplatesImpl(this.tokenSupplier), new UriBuilderImpl(uri)));
        }
    }

    public enum Schema {
        HTTP {
            @Override
            public String toString() {
                return "http";
            }
        },
        HTTPS {
            @Override
            public String toString() {
                return "https";
            }
        }
    }

    public interface SchemaStageBuilder {
        DomainStageBuilder schema(Schema schema);
    }

    public interface DomainStageBuilder {
        PortStageBuilder domain(String domain);
    }

    public interface PortStageBuilder {
        PathStageBuilder port(String port);
    }

    public interface PathStageBuilder {
        TokenStageBuilder path(String path);
    }

    public interface TokenStageBuilder {
        RootApi tokens(BasicAuthTokenSupplier tokenSupplier);
    }
}
