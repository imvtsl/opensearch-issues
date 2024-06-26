package org.vatsal.learn.issue14519;

import org.opensearch.action.get.GetResponse;
import org.opensearch.common.xcontent.LoggingDeprecationHandler;
import org.opensearch.common.xcontent.json.JsonXContent;
import org.opensearch.core.common.ParsingException;
import org.opensearch.core.xcontent.NamedXContentRegistry;
import org.opensearch.core.xcontent.XContentParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        printExceptionType("{foo}");
        printExceptionType("{\"found\":false}");
        printExceptionType("{\"_index\":\"foo\",\"_id\":\"bar\"}");
    }

    static void printExceptionType(String json) {
        System.out.println("String to parse: " + json);
        try (
                XContentParser parser = JsonXContent.jsonXContent
                        .createParser(NamedXContentRegistry.EMPTY, LoggingDeprecationHandler.INSTANCE, json)
        ) {
            GetResponse.fromXContent(parser);
        } catch (IOException io) {
            System.out.println("  Bad JSON, expected IOException: " + io.getMessage());
        } catch (ParsingException p) {
            System.out.println("  Missing fields, expected ParsingException: " + p.getMessage());
        } catch (Exception e) {
            System.out.println("  Missing \"found\", unexpected Exception: " + e.getClass() + ", " + e.getMessage());
        }
        System.out.println();
    }
}