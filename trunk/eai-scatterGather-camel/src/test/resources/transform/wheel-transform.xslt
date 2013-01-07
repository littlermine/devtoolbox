<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:template match="/">
        <ser:retrieveWheel xmlns:ser="http://service.wheel.devtoolbox.org/">
            <arg0>
                <type><xsl:value-of select="/car/wheel"/></type>
            </arg0>
        </ser:retrieveWheel>
    </xsl:template>

</xsl:stylesheet>