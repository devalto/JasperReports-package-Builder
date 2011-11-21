# JasperReports package builder
This tool builds JasperReports deb files ready to be published in an apt repository.

We publish the deb packaged JasperReports in [our apt repository](http://repo.ada-consult.com).

## Dependencies

 * [Phing](http://www.phing.info)
 * fakeroot Ubuntu package

## Usage

First, you have to download JasperReports library at [JasperForge](http://www.jasperforge.org). Then, you put the extracted library in the src directory. Make sure you put the root of the archive, JasperSoft name the root folder jasperreports-x.y.z. The build file depends on this name structure.

Then, you call phing :

```bash
phing -Dpackage.version=4.0.1
```

You should have a deb package in your dist directory.

# References
 * http://tldp.org/HOWTO/html_single/Debian-Binary-Package-Building-HOWTO/
