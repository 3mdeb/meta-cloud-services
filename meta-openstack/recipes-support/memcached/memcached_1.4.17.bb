SUMMARY = "A high-performance memory object caching system"
DESCRIPTION = "\
 Danga Interactive developed memcached to enhance the speed of LiveJournal.com, \
 a site which was already doing 20 million+ dynamic page views per day for 1 \
 million users with a bunch of webservers and a bunch of database servers. \
 memcached dropped the database load to almost nothing, yielding faster page \
 load times for users, better resource utilization, and faster access to the \
 databases on a memcache miss. \
 . \
 memcached optimizes specific high-load serving applications that are designed \
 to take advantage of its versatile no-locking memory access system. Clients \
 are available in several different programming languages, to suit the needs \
 of the specific application. Traditionally this has been used in mod_perl \
 apps to avoid storing large chunks of data in Apache memory, and to share \
 this burden across several machines."

HOMEPAGE = "http://www.danga.com/memcached/"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://COPYING;md5=7e5ded7363d335e1bb18013ca08046ff"

inherit autotools autotools-brokensep

DEPENDS += "libevent"
RDEPENDS_${PN} += "perl perl-module-posix perl-module-autoloader perl-module-tie-hash"

SRC_URI = "http://www.memcached.org/files/${PN}-${PV}.tar.gz"

SRC_URI[md5sum] = "46402dfbd7faadf6182283dbbd18b1a6"
SRC_URI[sha256sum] = "d9173ef6d99ba798c982ea4566cb4f0e64eb23859fdbf9926a89999d8cdc0458"

python __anonymous () {
    endianness = d.getVar('SITEINFO_ENDIANNESS', True)
    if endianness == 'le':
        d.setVar('EXTRA_OECONF', "ac_cv_c_endian=little")
    else:
        d.setVar('EXTRA_OECONF', "ac_cv_c_endian=big")
}

inherit update-rc.d

INITSCRIPT_NAME = "memcached"
INITSCRIPT_PARAMS = "defaults"

do_install_append() {
	install -D -m 755 ${S}/scripts/memcached-init ${D}${sysconfdir}/init.d/memcached 
	mkdir -p ${D}/usr/share/memcached/scripts
	install -m 755 ${S}/scripts/memcached-tool ${D}/usr/share/memcached/scripts
	install -m 755 ${S}/scripts/start-memcached ${D}/usr/share/memcached/scripts
}
