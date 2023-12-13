package ms.sora.mod.morebuilding.common;

/**
 * MBInfo is an information class.
 * <p>
 * This class contains mod information.
 *
 * @since 0.1.0-alpha1
 */
@SuppressWarnings("unused")
public final class MBInfo {
    /**
     * A mod description
     */
    public static final String MOD_DESCRIPTION = """
        ${mod_description}""";
    /**
     * A mod id
     */
    public static final String MOD_ID = "${mod_id}";
    /**
     * A mod name
     */
    public static final String MOD_NAME = "${mod_name}";
    /**
     * A mod version
     */
    public static final String MOD_VERSION = "${mod_version}";

    private MBInfo() {
    }
}
