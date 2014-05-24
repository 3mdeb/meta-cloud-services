#
# Copyright (C) 2014 Wind River Systems, Inc.
#
SUMMARY = "A collection of tools that extend Hashes and make them more useful."
DESCRIPTION = "A collection of tools that extend Hashes and make them more useful."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dd1ec82dd4ea7ed5eebb24bebec9f068"

PR = "r0"

BPV = "2.1.1"
PV = "${BPV}"
SRCREV = "378c62ab417dc05555fefa37f50ba908d7a98af5"

S = "${WORKDIR}/git"

SRC_URI = " \
    git://github.com/intridea/hashie.git \
    "

inherit ruby

BBCLASSEXTEND = "native"
