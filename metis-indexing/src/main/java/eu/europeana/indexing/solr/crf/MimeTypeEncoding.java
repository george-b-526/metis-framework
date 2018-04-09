package eu.europeana.indexing.solr.crf;

import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Generates the common pure tags (mimetype and mediatype)
 */
public enum MimeTypeEncoding {

  TYPE_0("", 0),
  TYPE_1("application/andrew-inset", 1),
  TYPE_2("application/applixware", 2),
  TYPE_3("application/atom+xml", 3),
  TYPE_4("application/atomcat+xml", 4),
  TYPE_5("application/atomsvc+xml", 5),
  TYPE_6("application/ccxml+xml", 6),
  TYPE_7("application/cdmi-capability", 7),
  TYPE_8("application/cdmi-container", 8),
  TYPE_9("application/cdmi-domain", 9),
  TYPE_10("application/cdmi-object", 10),
  TYPE_11("application/cdmi-queue", 11),
  TYPE_12("application/cu-seeme", 12),
  TYPE_13("application/davmount+xml", 13),
  TYPE_14("application/docbook+xml", 14),
  TYPE_15("application/dssc+der", 15),
  TYPE_16("application/dssc+xml", 16),
  TYPE_17("application/ecmascript", 17),
  TYPE_18("application/emma+xml", 18),
  TYPE_19("application/epub+zip", 19),
  TYPE_20("application/exi", 20),
  TYPE_21("application/font-tdpfr", 21),
  TYPE_22("application/gml+xml", 22),
  TYPE_23("application/gpx+xml", 23),
  TYPE_24("application/gxf", 24),
  TYPE_25("application/hyperstudio", 25),
  TYPE_26("application/inkml+xml", 26),
  TYPE_27("application/ipfix", 27),
  TYPE_28("application/java-archive", 28),
  TYPE_29("application/java-serialized-object", 29),
  TYPE_30("application/java-vm", 30),
  TYPE_31("application/javascript", 31),
  TYPE_32("application/json", 32),
  TYPE_33("application/jsonml+json", 33),
  TYPE_34("application/lost+xml", 34),
  TYPE_35("application/mac-binhex40", 35),
  TYPE_36("application/mac-compactpro", 36),
  TYPE_37("application/mads+xml", 37),
  TYPE_38("application/marc", 38),
  TYPE_39("application/marcxml+xml", 39),
  TYPE_40("application/mathematica", 40),
  TYPE_41("application/mathml+xml", 41),
  TYPE_42("application/mbox", 42),
  TYPE_43("application/mediaservercontrol+xml", 43),
  TYPE_44("application/metalink+xml", 44),
  TYPE_45("application/metalink4+xml", 45),
  TYPE_46("application/mets+xml", 46),
  TYPE_47("application/mods+xml", 47),
  TYPE_48("application/mp21", 48),
  TYPE_49("application/mp4", 49),
  TYPE_50("application/msword", 50),
  TYPE_51("application/mxf", 51),
  TYPE_52("application/octet-stream", 52),
  TYPE_53("application/oda", 53),
  TYPE_54("application/oebps-package+xml", 54),
  TYPE_55("application/ogg", 55),
  TYPE_56("application/omdoc+xml", 56),
  TYPE_57("application/onenote", 57),
  TYPE_58("application/oxps", 58),
  TYPE_59("application/patch-ops-error+xml", 59),
  TYPE_60("application/pdf", 60),
  TYPE_61("application/pgp-encrypted", 61),
  TYPE_62("application/pgp-signature", 62),
  TYPE_63("application/pics-rules", 63),
  TYPE_64("application/pkcs10", 64),
  TYPE_65("application/pkcs7-mime", 65),
  TYPE_66("application/pkcs7-signature", 66),
  TYPE_67("application/pkcs8", 67),
  TYPE_68("application/pkix-attr-cert", 68),
  TYPE_69("application/pkix-cert", 69),
  TYPE_70("application/pkix-crl", 70),
  TYPE_71("application/pkix-pkipath", 71),
  TYPE_72("application/pkixcmp", 72),
  TYPE_73("application/pls+xml", 73),
  TYPE_74("application/postscript", 74),
  TYPE_75("application/prs.cww", 75),
  TYPE_76("application/pskc+xml", 76),
  TYPE_77("application/rdf+xml", 77),
  TYPE_78("application/reginfo+xml", 78),
  TYPE_79("application/relax-ng-compact-syntax", 79),
  TYPE_80("application/resource-lists+xml", 80),
  TYPE_81("application/resource-lists-diff+xml", 81),
  TYPE_82("application/rls-services+xml", 82),
  TYPE_83("application/rpki-ghostbusters", 83),
  TYPE_84("application/rpki-manifest", 84),
  TYPE_85("application/rpki-roa", 85),
  TYPE_86("application/rsd+xml", 86),
  TYPE_87("application/rss+xml", 87),
  TYPE_88("application/rtf", 88),
  TYPE_89("application/sbml+xml", 89),
  TYPE_90("application/scvp-cv-request", 90),
  TYPE_91("application/scvp-cv-response", 91),
  TYPE_92("application/scvp-vp-request", 92),
  TYPE_93("application/scvp-vp-response", 93),
  TYPE_94("application/sdp", 94),
  TYPE_95("application/set-payment-initiation", 95),
  TYPE_96("application/set-registration-initiation", 96),
  TYPE_97("application/shf+xml", 97),
  TYPE_98("application/smil+xml", 98),
  TYPE_99("application/sparql-query", 99),
  TYPE_100("application/sparql-results+xml", 100),
  TYPE_101("application/srgs", 101),
  TYPE_102("application/srgs+xml", 102),
  TYPE_103("application/sru+xml", 103),
  TYPE_104("application/ssdl+xml", 104),
  TYPE_105("application/ssml+xml", 105),
  TYPE_106("application/tei+xml", 106),
  TYPE_107("application/thraud+xml", 107),
  TYPE_108("application/timestamped-data", 108),
  TYPE_109("application/vnd.3gpp.pic-bw-large", 109),
  TYPE_110("application/vnd.3gpp.pic-bw-small", 110),
  TYPE_111("application/vnd.3gpp.pic-bw-var", 111),
  TYPE_112("application/vnd.3gpp2.tcap", 112),
  TYPE_113("application/vnd.3m.post-it-notes", 113),
  TYPE_114("application/vnd.accpac.simply.aso", 114),
  TYPE_115("application/vnd.accpac.simply.imp", 115),
  TYPE_116("application/vnd.acucobol", 116),
  TYPE_117("application/vnd.acucorp", 117),
  TYPE_118("application/vnd.adobe.air-application-installer-package+zip", 118),
  TYPE_119("application/vnd.adobe.formscentral.fcdt", 119),
  TYPE_120("application/vnd.adobe.fxp", 120),
  TYPE_121("application/vnd.adobe.xdp+xml", 121),
  TYPE_122("application/vnd.adobe.xfdf", 122),
  TYPE_123("application/vnd.ahead.space", 123),
  TYPE_124("application/vnd.airzip.filesecure.azf", 124),
  TYPE_125("application/vnd.airzip.filesecure.azs", 125),
  TYPE_126("application/vnd.amazon.ebook", 126),
  TYPE_127("application/vnd.americandynamics.acc", 127),
  TYPE_128("application/vnd.amiga.ami", 128),
  TYPE_129("application/vnd.android.package-archive", 129),
  TYPE_130("application/vnd.anser-web-certificate-issue-initiation", 130),
  TYPE_131("application/vnd.anser-web-funds-transfer-initiation", 131),
  TYPE_132("application/vnd.antix.game-component", 132),
  TYPE_133("application/vnd.apple.installer+xml", 133),
  TYPE_134("application/vnd.apple.mpegurl", 134),
  TYPE_135("application/vnd.aristanetworks.swi", 135),
  TYPE_136("application/vnd.astraea-software.iota", 136),
  TYPE_137("application/vnd.audiograph", 137),
  TYPE_138("application/vnd.blueice.multipass", 138),
  TYPE_139("application/vnd.bmi", 139),
  TYPE_140("application/vnd.businessobjects", 140),
  TYPE_141("application/vnd.chemdraw+xml", 141),
  TYPE_142("application/vnd.chipnuts.karaoke-mmd", 142),
  TYPE_143("application/vnd.cinderella", 143),
  TYPE_144("application/vnd.claymore", 144),
  TYPE_145("application/vnd.cloanto.rp9", 145),
  TYPE_146("application/vnd.clonk.c4group", 146),
  TYPE_147("application/vnd.cluetrust.cartomobile-config", 147),
  TYPE_148("application/vnd.cluetrust.cartomobile-config-pkg", 148),
  TYPE_149("application/vnd.commonspace", 149),
  TYPE_150("application/vnd.contact.cmsg", 150),
  TYPE_151("application/vnd.cosmocaller", 151),
  TYPE_152("application/vnd.crick.clicker", 152),
  TYPE_153("application/vnd.crick.clicker.keyboard", 153),
  TYPE_154("application/vnd.crick.clicker.palette", 154),
  TYPE_155("application/vnd.crick.clicker.template", 155),
  TYPE_156("application/vnd.crick.clicker.wordbank", 156),
  TYPE_157("application/vnd.criticaltools.wbs+xml", 157),
  TYPE_158("application/vnd.ctc-posml", 158),
  TYPE_159("application/vnd.cups-ppd", 159),
  TYPE_160("application/vnd.curl.car", 160),
  TYPE_161("application/vnd.curl.pcurl", 161),
  TYPE_162("application/vnd.dart", 162),
  TYPE_163("application/vnd.data-vision.rdz", 163),
  TYPE_164("application/vnd.dece.data", 164),
  TYPE_165("application/vnd.dece.ttml+xml", 165),
  TYPE_166("application/vnd.dece.unspecified", 166),
  TYPE_167("application/vnd.dece.zip", 167),
  TYPE_168("application/vnd.denovo.fcselayout-link", 168),
  TYPE_169("application/vnd.dna", 169),
  TYPE_170("application/vnd.dolby.mlp", 170),
  TYPE_171("application/vnd.dpgraph", 171),
  TYPE_172("application/vnd.dreamfactory", 172),
  TYPE_173("application/vnd.ds-keypoint", 173),
  TYPE_174("application/vnd.dvb.ait", 174),
  TYPE_175("application/vnd.dvb.service", 175),
  TYPE_176("application/vnd.dynageo", 176),
  TYPE_177("application/vnd.ecowin.chart", 177),
  TYPE_178("application/vnd.enliven", 178),
  TYPE_179("application/vnd.epson.esf", 179),
  TYPE_180("application/vnd.epson.msf", 180),
  TYPE_181("application/vnd.epson.quickanime", 181),
  TYPE_182("application/vnd.epson.salt", 182),
  TYPE_183("application/vnd.epson.ssf", 183),
  TYPE_184("application/vnd.eszigno3+xml", 184),
  TYPE_185("application/vnd.ezpix-album", 185),
  TYPE_186("application/vnd.ezpix-package", 186),
  TYPE_187("application/vnd.fdf", 187),
  TYPE_188("application/vnd.fdsn.mseed", 188),
  TYPE_189("application/vnd.fdsn.seed", 189),
  TYPE_190("application/vnd.flographit", 190),
  TYPE_191("application/vnd.fluxtime.clip", 191),
  TYPE_192("application/vnd.framemaker", 192),
  TYPE_193("application/vnd.frogans.fnc", 193),
  TYPE_194("application/vnd.frogans.ltf", 194),
  TYPE_195("application/vnd.fsc.weblaunch", 195),
  TYPE_196("application/vnd.fujitsu.oasys", 196),
  TYPE_197("application/vnd.fujitsu.oasys2", 197),
  TYPE_198("application/vnd.fujitsu.oasys3", 198),
  TYPE_199("application/vnd.fujitsu.oasysgp", 199),
  TYPE_200("application/vnd.fujitsu.oasysprs", 200),
  TYPE_201("application/vnd.fujixerox.ddd", 201),
  TYPE_202("application/vnd.fujixerox.docuworks", 202),
  TYPE_203("application/vnd.fujixerox.docuworks.binder", 203),
  TYPE_204("application/vnd.fuzzysheet", 204),
  TYPE_205("application/vnd.genomatix.tuxedo", 205),
  TYPE_206("application/vnd.geogebra.file", 206),
  TYPE_207("application/vnd.geogebra.tool", 207),
  TYPE_208("application/vnd.geometry-explorer", 208),
  TYPE_209("application/vnd.geonext", 209),
  TYPE_210("application/vnd.geoplan", 210),
  TYPE_211("application/vnd.geospace", 211),
  TYPE_212("application/vnd.gmx", 212),
  TYPE_213("application/vnd.google-earth.kml+xml", 213),
  TYPE_214("application/vnd.google-earth.kmz", 214),
  TYPE_215("application/vnd.grafeq", 215),
  TYPE_216("application/vnd.groove-account", 216),
  TYPE_217("application/vnd.groove-help", 217),
  TYPE_218("application/vnd.groove-identity-message", 218),
  TYPE_219("application/vnd.groove-injector", 219),
  TYPE_220("application/vnd.groove-tool-message", 220),
  TYPE_221("application/vnd.groove-tool-template", 221),
  TYPE_222("application/vnd.groove-vcard", 222),
  TYPE_223("application/vnd.hal+xml", 223),
  TYPE_224("application/vnd.handheld-entertainment+xml", 224),
  TYPE_225("application/vnd.hbci", 225),
  TYPE_226("application/vnd.hhe.lesson-player", 226),
  TYPE_227("application/vnd.hp-hpgl", 227),
  TYPE_228("application/vnd.hp-hpid", 228),
  TYPE_229("application/vnd.hp-hps", 229),
  TYPE_230("application/vnd.hp-jlyt", 230),
  TYPE_231("application/vnd.hp-pcl", 231),
  TYPE_232("application/vnd.hp-pclxl", 232),
  TYPE_233("application/vnd.hydrostatix.sof-data", 233),
  TYPE_234("application/vnd.ibm.minipay", 234),
  TYPE_235("application/vnd.ibm.modcap", 235),
  TYPE_236("application/vnd.ibm.rights-management", 236),
  TYPE_237("application/vnd.ibm.secure-container", 237),
  TYPE_238("application/vnd.iccprofile", 238),
  TYPE_239("application/vnd.igloader", 239),
  TYPE_240("application/vnd.immervision-ivp", 240),
  TYPE_241("application/vnd.immervision-ivu", 241),
  TYPE_242("application/vnd.insors.igm", 242),
  TYPE_243("application/vnd.intercon.formnet", 243),
  TYPE_244("application/vnd.intergeo", 244),
  TYPE_245("application/vnd.intu.qbo", 245),
  TYPE_246("application/vnd.intu.qfx", 246),
  TYPE_247("application/vnd.ipunplugged.rcprofile", 247),
  TYPE_248("application/vnd.irepository.package+xml", 248),
  TYPE_249("application/vnd.is-xpr", 249),
  TYPE_250("application/vnd.isac.fcs", 250),
  TYPE_251("application/vnd.jam", 251),
  TYPE_252("application/vnd.jcp.javame.midlet-rms", 252),
  TYPE_253("application/vnd.jisp", 253),
  TYPE_254("application/vnd.joost.joda-archive", 254),
  TYPE_255("application/vnd.kahootz", 255),
  TYPE_256("application/vnd.kde.karbon", 256),
  TYPE_257("application/vnd.kde.kchart", 257),
  TYPE_258("application/vnd.kde.kformula", 258),
  TYPE_259("application/vnd.kde.kivio", 259),
  TYPE_260("application/vnd.kde.kontour", 260),
  TYPE_261("application/vnd.kde.kpresenter", 261),
  TYPE_262("application/vnd.kde.kspread", 262),
  TYPE_263("application/vnd.kde.kword", 263),
  TYPE_264("application/vnd.kenameaapp", 264),
  TYPE_265("application/vnd.kidspiration", 265),
  TYPE_266("application/vnd.kinar", 266),
  TYPE_267("application/vnd.koan", 267),
  TYPE_268("application/vnd.kodak-descriptor", 268),
  TYPE_269("application/vnd.las.las+xml", 269),
  TYPE_270("application/vnd.llamagraphics.life-balance.desktop", 270),
  TYPE_271("application/vnd.llamagraphics.life-balance.exchange+xml", 271),
  TYPE_272("application/vnd.lotus-1-2-3", 272),
  TYPE_273("application/vnd.lotus-approach", 273),
  TYPE_274("application/vnd.lotus-freelance", 274),
  TYPE_275("application/vnd.lotus-notes", 275),
  TYPE_276("application/vnd.lotus-organizer", 276),
  TYPE_277("application/vnd.lotus-screencam", 277),
  TYPE_278("application/vnd.lotus-wordpro", 278),
  TYPE_279("application/vnd.macports.portpkg", 279),
  TYPE_280("application/vnd.mcd", 280),
  TYPE_281("application/vnd.medcalcdata", 281),
  TYPE_282("application/vnd.mediastation.cdkey", 282),
  TYPE_283("application/vnd.mfer", 283),
  TYPE_284("application/vnd.mfmp", 284),
  TYPE_285("application/vnd.micrografx.flo", 285),
  TYPE_286("application/vnd.micrografx.igx", 286),
  TYPE_287("application/vnd.mif", 287),
  TYPE_288("application/vnd.mobius.daf", 288),
  TYPE_289("application/vnd.mobius.dis", 289),
  TYPE_290("application/vnd.mobius.mbk", 290),
  TYPE_291("application/vnd.mobius.mqy", 291),
  TYPE_292("application/vnd.mobius.msl", 292),
  TYPE_293("application/vnd.mobius.plc", 293),
  TYPE_294("application/vnd.mobius.txf", 294),
  TYPE_295("application/vnd.mophun.application", 295),
  TYPE_296("application/vnd.mophun.certificate", 296),
  TYPE_297("application/vnd.mozilla.xul+xml", 297),
  TYPE_298("application/vnd.ms-artgalry", 298),
  TYPE_299("application/vnd.ms-cab-compressed", 299),
  TYPE_300("application/vnd.ms-excel", 300),
  TYPE_301("application/vnd.ms-excel.addin.macroenabled.12", 301),
  TYPE_302("application/vnd.ms-excel.sheet.binary.macroenabled.12", 302),
  TYPE_303("application/vnd.ms-excel.sheet.macroenabled.12", 303),
  TYPE_304("application/vnd.ms-excel.template.macroenabled.12", 304),
  TYPE_305("application/vnd.ms-fontobject", 305),
  TYPE_306("application/vnd.ms-htmlhelp", 306),
  TYPE_307("application/vnd.ms-ims", 307),
  TYPE_308("application/vnd.ms-lrm", 308),
  TYPE_309("application/vnd.ms-officetheme", 309),
  TYPE_310("application/vnd.ms-pki.seccat", 310),
  TYPE_311("application/vnd.ms-pki.stl", 311),
  TYPE_312("application/vnd.ms-powerpoint", 312),
  TYPE_313("application/vnd.ms-powerpoint.addin.macroenabled.12", 313),
  TYPE_314("application/vnd.ms-powerpoint.presentation.macroenabled.12", 314),
  TYPE_315("application/vnd.ms-powerpoint.slide.macroenabled.12", 315),
  TYPE_316("application/vnd.ms-powerpoint.slideshow.macroenabled.12", 316),
  TYPE_317("application/vnd.ms-powerpoint.template.macroenabled.12", 317),
  TYPE_318("application/vnd.ms-project", 318),
  TYPE_319("application/vnd.ms-word.document.macroenabled.12", 319),
  TYPE_320("application/vnd.ms-word.template.macroenabled.12", 320),
  TYPE_321("application/vnd.ms-works", 321),
  TYPE_322("application/vnd.ms-wpl", 322),
  TYPE_323("application/vnd.ms-xpsdocument", 323),
  TYPE_324("application/vnd.mseq", 324),
  TYPE_325("application/vnd.musician", 325),
  TYPE_326("application/vnd.muvee.style", 326),
  TYPE_327("application/vnd.mynfc", 327),
  TYPE_328("application/vnd.neurolanguage.nlu", 328),
  TYPE_329("application/vnd.nitf", 329),
  TYPE_330("application/vnd.noblenet-directory", 330),
  TYPE_331("application/vnd.noblenet-sealer", 331),
  TYPE_332("application/vnd.noblenet-web", 332),
  TYPE_333("application/vnd.nokia.n-gage.data", 333),
  TYPE_334("application/vnd.nokia.n-gage.symbian.install", 334),
  TYPE_335("application/vnd.nokia.radio-preset", 335),
  TYPE_336("application/vnd.nokia.radio-presets", 336),
  TYPE_337("application/vnd.novadigm.edm", 337),
  TYPE_338("application/vnd.novadigm.edx", 338),
  TYPE_339("application/vnd.novadigm.ext", 339),
  TYPE_340("application/vnd.oasis.opendocument.chart", 340),
  TYPE_341("application/vnd.oasis.opendocument.chart-template", 341),
  TYPE_342("application/vnd.oasis.opendocument.database", 342),
  TYPE_343("application/vnd.oasis.opendocument.formula", 343),
  TYPE_344("application/vnd.oasis.opendocument.formula-template", 344),
  TYPE_345("application/vnd.oasis.opendocument.graphics", 345),
  TYPE_346("application/vnd.oasis.opendocument.graphics-template", 346),
  TYPE_347("application/vnd.oasis.opendocument.image", 347),
  TYPE_348("application/vnd.oasis.opendocument.image-template", 348),
  TYPE_349("application/vnd.oasis.opendocument.presentation", 349),
  TYPE_350("application/vnd.oasis.opendocument.presentation-template", 350),
  TYPE_351("application/vnd.oasis.opendocument.spreadsheet", 351),
  TYPE_352("application/vnd.oasis.opendocument.spreadsheet-template", 352),
  TYPE_353("application/vnd.oasis.opendocument.text", 353),
  TYPE_354("application/vnd.oasis.opendocument.text-master", 354),
  TYPE_355("application/vnd.oasis.opendocument.text-template", 355),
  TYPE_356("application/vnd.oasis.opendocument.text-web", 356),
  TYPE_357("application/vnd.olpc-sugar", 357),
  TYPE_358("application/vnd.oma.dd2+xml", 358),
  TYPE_359("application/vnd.openofficeorg.extension", 359),
  TYPE_360("application/vnd.openxmlformats-officedocument.presentationml.presentation", 360),
  TYPE_361("application/vnd.openxmlformats-officedocument.presentationml.slide", 361),
  TYPE_362("application/vnd.openxmlformats-officedocument.presentationml.slideshow", 362),
  TYPE_363("application/vnd.openxmlformats-officedocument.presentationml.template", 363),
  TYPE_364("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", 364),
  TYPE_365("application/vnd.openxmlformats-officedocument.spreadsheetml.template", 365),
  TYPE_366("application/vnd.openxmlformats-officedocument.wordprocessingml.document", 366),
  TYPE_367("application/vnd.openxmlformats-officedocument.wordprocessingml.template", 367),
  TYPE_368("application/vnd.osgeo.mapguide.package", 368),
  TYPE_369("application/vnd.osgi.dp", 369),
  TYPE_370("application/vnd.osgi.subsystem", 370),
  TYPE_371("application/vnd.palm", 371),
  TYPE_372("application/vnd.pawaafile", 372),
  TYPE_373("application/vnd.pg.format", 373),
  TYPE_374("application/vnd.pg.osasli", 374),
  TYPE_375("application/vnd.picsel", 375),
  TYPE_376("application/vnd.pmi.widget", 376),
  TYPE_377("application/vnd.pocketlearn", 377),
  TYPE_378("application/vnd.powerbuilder6", 378),
  TYPE_379("application/vnd.previewsystems.box", 379),
  TYPE_380("application/vnd.proteus.magazine", 380),
  TYPE_381("application/vnd.publishare-delta-tree", 381),
  TYPE_382("application/vnd.pvi.ptid1", 382),
  TYPE_383("application/vnd.quark.quarkxpress", 383),
  TYPE_384("application/vnd.realvnc.bed", 384),
  TYPE_385("application/vnd.recordare.musicxml", 385),
  TYPE_386("application/vnd.recordare.musicxml+xml", 386),
  TYPE_387("application/vnd.rig.cryptonote", 387),
  TYPE_388("application/vnd.rim.cod", 388),
  TYPE_389("application/vnd.rn-realmedia", 389),
  TYPE_390("application/vnd.rn-realmedia-vbr", 390),
  TYPE_391("application/vnd.route66.link66+xml", 391),
  TYPE_392("application/vnd.sailingtracker.track", 392),
  TYPE_393("application/vnd.seemail", 393),
  TYPE_394("application/vnd.sema", 394),
  TYPE_395("application/vnd.semd", 395),
  TYPE_396("application/vnd.semf", 396),
  TYPE_397("application/vnd.shana.informed.formdata", 397),
  TYPE_398("application/vnd.shana.informed.formtemplate", 398),
  TYPE_399("application/vnd.shana.informed.interchange", 399),
  TYPE_400("application/vnd.shana.informed.package", 400),
  TYPE_401("application/vnd.simtech-mindmapper", 401),
  TYPE_402("application/vnd.smaf", 402),
  TYPE_403("application/vnd.smart.teacher", 403),
  TYPE_404("application/vnd.solent.sdkm+xml", 404),
  TYPE_405("application/vnd.spotfire.dxp", 405),
  TYPE_406("application/vnd.spotfire.sfs", 406),
  TYPE_407("application/vnd.stardivision.calc", 407),
  TYPE_408("application/vnd.stardivision.draw", 408),
  TYPE_409("application/vnd.stardivision.impress", 409),
  TYPE_410("application/vnd.stardivision.math", 410),
  TYPE_411("application/vnd.stardivision.writer", 411),
  TYPE_412("application/vnd.stardivision.writer-global", 412),
  TYPE_413("application/vnd.stepmania.package", 413),
  TYPE_414("application/vnd.stepmania.stepchart", 414),
  TYPE_415("application/vnd.sun.xml.calc", 415),
  TYPE_416("application/vnd.sun.xml.calc.template", 416),
  TYPE_417("application/vnd.sun.xml.draw", 417),
  TYPE_418("application/vnd.sun.xml.draw.template", 418),
  TYPE_419("application/vnd.sun.xml.impress", 419),
  TYPE_420("application/vnd.sun.xml.impress.template", 420),
  TYPE_421("application/vnd.sun.xml.math", 421),
  TYPE_422("application/vnd.sun.xml.writer", 422),
  TYPE_423("application/vnd.sun.xml.writer.global", 423),
  TYPE_424("application/vnd.sun.xml.writer.template", 424),
  TYPE_425("application/vnd.sus-calendar", 425),
  TYPE_426("application/vnd.svd", 426),
  TYPE_427("application/vnd.symbian.install", 427),
  TYPE_428("application/vnd.syncml+xml", 428),
  TYPE_429("application/vnd.syncml.dm+wbxml", 429),
  TYPE_430("application/vnd.syncml.dm+xml", 430),
  TYPE_431("application/vnd.tao.intent-module-archive", 431),
  TYPE_432("application/vnd.tcpdump.pcap", 432),
  TYPE_433("application/vnd.tmobile-livetv", 433),
  TYPE_434("application/vnd.trid.tpt", 434),
  TYPE_435("application/vnd.triscape.mxs", 435),
  TYPE_436("application/vnd.trueapp", 436),
  TYPE_437("application/vnd.ufdl", 437),
  TYPE_438("application/vnd.uiq.theme", 438),
  TYPE_439("application/vnd.umajin", 439),
  TYPE_440("application/vnd.unity", 440),
  TYPE_441("application/vnd.uoml+xml", 441),
  TYPE_442("application/vnd.vcx", 442),
  TYPE_443("application/vnd.visio", 443),
  TYPE_444("application/vnd.visionary", 444),
  TYPE_445("application/vnd.vsf", 445),
  TYPE_446("application/vnd.wap.wbxml", 446),
  TYPE_447("application/vnd.wap.wmlc", 447),
  TYPE_448("application/vnd.wap.wmlscriptc", 448),
  TYPE_449("application/vnd.webturbo", 449),
  TYPE_450("application/vnd.wolfram.player", 450),
  TYPE_451("application/vnd.wordperfect", 451),
  TYPE_452("application/vnd.wqd", 452),
  TYPE_453("application/vnd.wt.stf", 453),
  TYPE_454("application/vnd.xara", 454),
  TYPE_455("application/vnd.xfdl", 455),
  TYPE_456("application/vnd.yamaha.hv-dic", 456),
  TYPE_457("application/vnd.yamaha.hv-script", 457),
  TYPE_458("application/vnd.yamaha.hv-voice", 458),
  TYPE_459("application/vnd.yamaha.openscoreformat", 459),
  TYPE_460("application/vnd.yamaha.openscoreformat.osfpvg+xml", 460),
  TYPE_461("application/vnd.yamaha.smaf-audio", 461),
  TYPE_462("application/vnd.yamaha.smaf-phrase", 462),
  TYPE_463("application/vnd.yellowriver-custom-menu", 463),
  TYPE_464("application/vnd.zul", 464),
  TYPE_465("application/vnd.zzazz.deck+xml", 465),
  TYPE_466("application/voicexml+xml", 466),
  TYPE_467("application/widget", 467),
  TYPE_468("application/winhlp", 468),
  TYPE_469("application/wsdl+xml", 469),
  TYPE_470("application/wspolicy+xml", 470),
  TYPE_471("application/x-7z-compressed", 471),
  TYPE_472("application/x-abiword", 472),
  TYPE_473("application/x-ace-compressed", 473),
  TYPE_474("application/x-apple-diskimage", 474),
  TYPE_475("application/x-authorware-bin", 475),
  TYPE_476("application/x-authorware-map", 476),
  TYPE_477("application/x-authorware-seg", 477),
  TYPE_478("application/x-bcpio", 478),
  TYPE_479("application/x-bittorrent", 479),
  TYPE_480("application/x-blorb", 480),
  TYPE_481("application/x-bzip", 481),
  TYPE_482("application/x-bzip2", 482),
  TYPE_483("application/x-cbr", 483),
  TYPE_484("application/x-cdlink", 484),
  TYPE_485("application/x-cfs-compressed", 485),
  TYPE_486("application/x-chat", 486),
  TYPE_487("application/x-chess-pgn", 487),
  TYPE_488("application/x-conference", 488),
  TYPE_489("application/x-cpio", 489),
  TYPE_490("application/x-csh", 490),
  TYPE_491("application/x-debian-package", 491),
  TYPE_492("application/x-dgc-compressed", 492),
  TYPE_493("application/x-director", 493),
  TYPE_494("application/x-doom", 494),
  TYPE_495("application/x-dtbncx+xml", 495),
  TYPE_496("application/x-dtbook+xml", 496),
  TYPE_497("application/x-dtbresource+xml", 497),
  TYPE_498("application/x-dvi", 498),
  TYPE_499("application/x-envoy", 499),
  TYPE_500("application/x-eva", 500),
  TYPE_501("application/x-font-bdf", 501),
  TYPE_502("application/x-font-ghostscript", 502),
  TYPE_503("application/x-font-linux-psf", 503),
  TYPE_504("application/x-font-otf", 504),
  TYPE_505("application/x-font-pcf", 505),
  TYPE_506("application/x-font-snf", 506),
  TYPE_507("application/x-font-ttf", 507),
  TYPE_508("application/x-font-type1", 508),
  TYPE_509("application/x-font-woff", 509),
  TYPE_510("application/x-freearc", 510),
  TYPE_511("application/x-futuresplash", 511),
  TYPE_512("application/x-gca-compressed", 512),
  TYPE_513("application/x-glulx", 513),
  TYPE_514("application/x-gnumeric", 514),
  TYPE_515("application/x-gramps-xml", 515),
  TYPE_516("application/x-gtar", 516),
  TYPE_517("application/x-hdf", 517),
  TYPE_518("application/x-install-instructions", 518),
  TYPE_519("application/x-iso9660-image", 519),
  TYPE_520("application/x-java-jnlp-file", 520),
  TYPE_521("application/x-latex", 521),
  TYPE_522("application/x-lzh-compressed", 522),
  TYPE_523("application/x-mie", 523),
  TYPE_524("application/x-mobipocket-ebook", 524),
  TYPE_525("application/x-ms-application", 525),
  TYPE_526("application/x-ms-shortcut", 526),
  TYPE_527("application/x-ms-wmd", 527),
  TYPE_528("application/x-ms-wmz", 528),
  TYPE_529("application/x-ms-xbap", 529),
  TYPE_530("application/x-msaccess", 530),
  TYPE_531("application/x-msbinder", 531),
  TYPE_532("application/x-mscardfile", 532),
  TYPE_533("application/x-msclip", 533),
  TYPE_534("application/x-msdownload", 534),
  TYPE_535("application/x-msmediaview", 535),
  TYPE_536("application/x-msmetafile", 536),
  TYPE_537("application/x-msmoney", 537),
  TYPE_538("application/x-mspublisher", 538),
  TYPE_539("application/x-msschedule", 539),
  TYPE_540("application/x-msterminal", 540),
  TYPE_541("application/x-mswrite", 541),
  TYPE_542("application/x-netcdf", 542),
  TYPE_543("application/x-nzb", 543),
  TYPE_544("application/x-pkcs12", 544),
  TYPE_545("application/x-pkcs7-certificates", 545),
  TYPE_546("application/x-pkcs7-certreqresp", 546),
  TYPE_547("application/x-rar-compressed", 547),
  TYPE_548("application/x-research-info-systems", 548),
  TYPE_549("application/x-sh", 549),
  TYPE_550("application/x-shar", 550),
  TYPE_551("application/x-shockwave-flash", 551),
  TYPE_552("application/x-silverlight-app", 552),
  TYPE_553("application/x-sql", 553),
  TYPE_554("application/x-stuffit", 554),
  TYPE_555("application/x-stuffitx", 555),
  TYPE_556("application/x-subrip", 556),
  TYPE_557("application/x-sv4cpio", 557),
  TYPE_558("application/x-sv4crc", 558),
  TYPE_559("application/x-t3vm-image", 559),
  TYPE_560("application/x-tads", 560),
  TYPE_561("application/x-tar", 561),
  TYPE_562("application/x-tcl", 562),
  TYPE_563("application/x-tex", 563),
  TYPE_564("application/x-tex-tfm", 564),
  TYPE_565("application/x-texinfo", 565),
  TYPE_566("application/x-tgif", 566),
  TYPE_567("application/x-ustar", 567),
  TYPE_568("application/x-wais-source", 568),
  TYPE_569("application/x-x509-ca-cert", 569),
  TYPE_570("application/x-xfig", 570),
  TYPE_571("application/x-xliff+xml", 571),
  TYPE_572("application/x-xpinstall", 572),
  TYPE_573("application/x-xz", 573),
  TYPE_574("application/x-zmachine", 574),
  TYPE_575("application/xaml+xml", 575),
  TYPE_576("application/xcap-diff+xml", 576),
  TYPE_577("application/xenc+xml", 577),
  TYPE_578("application/xhtml+xml", 578),
  TYPE_579("application/xml", 579),
  TYPE_580("application/xml-dtd", 580),
  TYPE_581("application/xop+xml", 581),
  TYPE_582("application/xproc+xml", 582),
  TYPE_583("application/xslt+xml", 583),
  TYPE_584("application/xspf+xml", 584),
  TYPE_585("application/xv+xml", 585),
  TYPE_586("application/yang", 586),
  TYPE_587("application/yin+xml", 587),
  TYPE_588("application/zip", 588),
  TYPE_589("audio/adpcm", 589),
  TYPE_590("audio/basic", 590),
  TYPE_591("audio/midi", 591),
  TYPE_592("audio/mp4", 592),
  TYPE_593("audio/mpeg", 593),
  TYPE_594("audio/ogg", 594),
  TYPE_595("audio/s3m", 595),
  TYPE_596("audio/silk", 596),
  TYPE_597("audio/vnd.dece.audio", 597),
  TYPE_598("audio/vnd.digital-winds", 598),
  TYPE_599("audio/vnd.dra", 599),
  TYPE_600("audio/vnd.dts", 600),
  TYPE_601("audio/vnd.dts.hd", 601),
  TYPE_602("audio/vnd.lucent.voice", 602),
  TYPE_603("audio/vnd.ms-playready.media.pya", 603),
  TYPE_604("audio/vnd.nuera.ecelp4800", 604),
  TYPE_605("audio/vnd.nuera.ecelp7470", 605),
  TYPE_606("audio/vnd.nuera.ecelp9600", 606),
  TYPE_607("audio/vnd.rip", 607),
  TYPE_608("audio/webm", 608),
  TYPE_609("audio/x-aac", 609),
  TYPE_610("audio/x-aiff", 610),
  TYPE_611("audio/x-caf", 611),
  TYPE_612("audio/x-flac", 612),
  TYPE_613("audio/x-matroska", 613),
  TYPE_614("audio/x-mpegurl", 614),
  TYPE_615("audio/x-ms-wax", 615),
  TYPE_616("audio/x-ms-wma", 616),
  TYPE_617("audio/x-pn-realaudio", 617),
  TYPE_618("audio/x-pn-realaudio-plugin", 618),
  TYPE_619("audio/x-wav", 619),
  TYPE_620("audio/xm", 620),
  TYPE_621("chemical/x-cdx", 621),
  TYPE_622("chemical/x-cif", 622),
  TYPE_623("chemical/x-cmdf", 623),
  TYPE_624("chemical/x-cml", 624),
  TYPE_625("chemical/x-csml", 625),
  TYPE_626("chemical/x-xyz", 626),
  TYPE_627("image/bmp", 627),
  TYPE_628("image/cgm", 628),
  TYPE_629("image/g3fax", 629),
  TYPE_630("image/gif", 630),
  TYPE_631("image/ief", 631),
  TYPE_632("image/jpeg", 632),
  TYPE_633("image/ktx", 633),
  TYPE_634("image/png", 634),
  TYPE_635("image/prs.btif", 635),
  TYPE_636("image/sgi", 636),
  TYPE_637("image/svg+xml", 637),
  TYPE_638("image/tiff", 638),
  TYPE_639("image/vnd.adobe.photoshop", 639),
  TYPE_640("image/vnd.dece.graphic", 640),
  TYPE_641("image/vnd.dvb.subtitle", 641),
  TYPE_642("image/vnd.djvu", 642),
  TYPE_643("image/vnd.dwg", 643),
  TYPE_644("image/vnd.dxf", 644),
  TYPE_645("image/vnd.fastbidsheet", 645),
  TYPE_646("image/vnd.fpx", 646),
  TYPE_647("image/vnd.fst", 647),
  TYPE_648("image/vnd.fujixerox.edmics-mmr", 648),
  TYPE_649("image/vnd.fujixerox.edmics-rlc", 649),
  TYPE_650("image/vnd.ms-modi", 650),
  TYPE_651("image/vnd.ms-photo", 651),
  TYPE_652("image/vnd.net-fpx", 652),
  TYPE_653("image/vnd.wap.wbmp", 653),
  TYPE_654("image/vnd.xiff", 654),
  TYPE_655("image/webp", 655),
  TYPE_656("image/x-3ds", 656),
  TYPE_657("image/x-cmu-raster", 657),
  TYPE_658("image/x-cmx", 658),
  TYPE_659("image/x-freehand", 659),
  TYPE_660("image/x-icon", 660),
  TYPE_661("image/x-mrsid-image", 661),
  TYPE_662("image/x-pcx", 662),
  TYPE_663("image/x-pict", 663),
  TYPE_664("image/x-portable-anymap", 664),
  TYPE_665("image/x-portable-bitmap", 665),
  TYPE_666("image/x-portable-graymap", 666),
  TYPE_667("image/x-portable-pixmap", 667),
  TYPE_668("image/x-rgb", 668),
  TYPE_669("image/x-tga", 669),
  TYPE_670("image/x-xbitmap", 670),
  TYPE_671("image/x-xpixmap", 671),
  TYPE_672("image/x-xwindowdump", 672),
  TYPE_673("message/rfc822", 673),
  TYPE_674("model/iges", 674),
  TYPE_675("model/mesh", 675),
  TYPE_676("model/vnd.collada+xml", 676),
  TYPE_677("model/vnd.dwf", 677),
  TYPE_678("model/vnd.gdl", 678),
  TYPE_679("model/vnd.gtw", 679),
  TYPE_680("model/vnd.mts", 680),
  TYPE_681("model/vnd.vtu", 681),
  TYPE_682("model/vrml", 682),
  TYPE_683("model/x3d+binary", 683),
  TYPE_684("model/x3d+vrml", 684),
  TYPE_685("model/x3d+xml", 685),
  TYPE_686("text/cache-manifest", 686),
  TYPE_687("text/calendar", 687),
  TYPE_688("text/css", 688),
  TYPE_689("text/csv", 689),
  TYPE_690("text/html", 690),
  TYPE_691("text/n3", 691),
  TYPE_692("text/plain", 692),
  TYPE_693("text/prs.lines.tag", 693),
  TYPE_694("text/richtext", 694),
  TYPE_695("text/sgml", 695),
  TYPE_696("text/tab-separated-values", 696),
  TYPE_697("text/troff", 697),
  TYPE_698("text/turtle", 698),
  TYPE_699("text/uri-list", 699),
  TYPE_700("text/vcard", 700),
  TYPE_701("text/vnd.curl", 701),
  TYPE_702("text/vnd.curl.dcurl", 702),
  TYPE_703("text/vnd.curl.scurl", 703),
  TYPE_704("text/vnd.curl.mcurl", 704),
  TYPE_705("text/vnd.dvb.subtitle", 705),
  TYPE_706("text/vnd.fly", 706),
  TYPE_707("text/vnd.fmi.flexstor", 707),
  TYPE_708("text/vnd.graphviz", 708),
  TYPE_709("text/vnd.in3d.3dml", 709),
  TYPE_710("text/vnd.in3d.spot", 710),
  TYPE_711("text/vnd.sun.j2me.app-descriptor", 711),
  TYPE_712("text/vnd.wap.wml", 712),
  TYPE_713("text/vnd.wap.wmlscript", 713),
  TYPE_714("text/x-asm", 714),
  TYPE_715("text/x-c", 715),
  TYPE_716("text/x-fortran", 716),
  TYPE_717("text/x-java-source", 717),
  TYPE_718("text/x-opml", 718),
  TYPE_719("text/x-pascal", 719),
  TYPE_720("text/x-nfo", 720),
  TYPE_721("text/x-setext", 721),
  TYPE_722("text/x-sfv", 722),
  TYPE_723("text/x-uuencode", 723),
  TYPE_724("text/x-vcalendar", 724),
  TYPE_725("text/x-vcard", 725),
  TYPE_726("video/3gpp", 726),
  TYPE_727("video/3gpp2", 727),
  TYPE_728("video/h261", 728),
  TYPE_729("video/h263", 729),
  TYPE_730("video/h264", 730),
  TYPE_731("video/jpeg", 731),
  TYPE_732("video/jpm", 732),
  TYPE_733("video/mj2", 733),
  TYPE_734("video/mp4", 734),
  TYPE_735("video/mpeg", 735),
  TYPE_736("video/ogg", 736),
  TYPE_737("video/quicktime", 737),
  TYPE_738("video/vnd.dece.hd", 738),
  TYPE_739("video/vnd.dece.mobile", 739),
  TYPE_740("video/vnd.dece.pd", 740),
  TYPE_741("video/vnd.dece.sd", 741),
  TYPE_742("video/vnd.dece.video", 742),
  TYPE_743("video/vnd.dvb.file", 743),
  TYPE_744("video/vnd.fvt", 744),
  TYPE_745("video/vnd.mpegurl", 745),
  TYPE_746("video/vnd.ms-playready.media.pyv", 746),
  TYPE_747("video/vnd.uvvu.mp4", 747),
  TYPE_748("video/vnd.vivo", 748),
  TYPE_749("video/webm", 749),
  TYPE_750("video/x-f4v", 750),
  TYPE_751("video/x-fli", 751),
  TYPE_752("video/x-flv", 752),
  TYPE_753("video/x-m4v", 753),
  TYPE_754("video/x-matroska", 754),
  TYPE_755("video/x-mng", 755),
  TYPE_756("video/x-ms-asf", 756),
  TYPE_757("video/x-ms-vob", 757),
  TYPE_758("video/x-ms-wm", 758),
  TYPE_759("video/x-ms-wmv", 759),
  TYPE_760("video/x-ms-wmx", 760),
  TYPE_761("video/x-ms-wvx", 761),
  TYPE_762("video/x-msvideo", 762),
  TYPE_763("video/x-sgi-movie", 763),
  TYPE_764("video/x-smv", 764),
  TYPE_765("x-conference/x-cooltalk", 765),
  TYPE_766("application/x-download", 766),
  TYPE_767("image/pjpeg", 767),
  TYPE_768("audio/flac", 768),
  TYPE_769("pdf", 769),
  TYPE_770("image", 770),
  TYPE_771("image/pjpeg", 771),
  TYPE_772("image/jpg", 772),
  TYPE_773("audio/x-mpeg", 773),
  TYPE_774("audio/mpeg3", 774),
  TYPE_775("image/x-ms-bmp", 775);

  private static BiMap<String, Integer> mimeTypeMap;

  private final String mimeType;
  private final int code;

  MimeTypeEncoding(String mimeType, int code) {
    this.mimeType = mimeType;
    this.code = code;
  }

  private static synchronized BiMap<String, Integer> getMimeTypeMap() {
    if (mimeTypeMap == null) {
      mimeTypeMap = HashBiMap.create(MimeTypeEncoding.values().length);
      for (MimeTypeEncoding encoding : MimeTypeEncoding.values()) {
        mimeTypeMap.put(encoding.mimeType, encoding.code);
      }
    }
    return mimeTypeMap;
  }

  /**
   * Codifies the given mimetype
   * 
   * @param type the mimetype of the resource
   * @return the integer represantation of the mimetype
   */
  public static Integer getMimeTypeCode(final String type) {
    Integer retval = StringUtils.isNotBlank(type) ? getMimeTypeMap().get(type.toLowerCase()) : null;
    return retval == null ? 0 : retval;
  }
}
