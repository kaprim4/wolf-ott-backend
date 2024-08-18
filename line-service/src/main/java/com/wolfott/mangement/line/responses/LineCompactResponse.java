package com.wolfott.mangement.line.responses;

import java.sql.Timestamp;

public record LineCompactResponse(Long id,
                                  Long expDate,
                                  Integer adminEnabled,
                                  Integer enabled,
                                  String adminNotes,
                                  String resellerNotes,
                                  String bouquet,
                                  String allowedOutputs,
                                  Integer maxConnections,
                                  Boolean isRestreamer,
                                  Boolean isTrial,
                                  Boolean isMag,
                                  Boolean isE2,
                                  Boolean isStalker,
                                  String allowedIps,
                                  String allowedUa,
                                  Long createdAt,
                                  Long pairId,
                                  Integer forceServerId,
                                  String asNumber,
                                  String ispDesc,
                                  String forcedCountry,
                                  Long packageId,
                                  String contact,
                                  Timestamp updated) {}
