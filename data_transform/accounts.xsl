<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" />
	<xsl:template match="/">
		<accounts>
			<xsl:apply-templates />
		</accounts>
	</xsl:template>
	<xsl:template match="account">
		<account email="{@email}">
			<name>
				<xsl:value-of select="name" />
			</name>
			<address country="{address/country}" city="{address/city}"
				street="{address/street}">
				<telephone number="{telephone}" />
			</address>
		</account>
	</xsl:template>
</xsl:stylesheet>