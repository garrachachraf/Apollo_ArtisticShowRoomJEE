configure the path of the upload folder in UploadfileService.java 
replace /tmp/ with any Folder path you want .

in your standalone.xml make those modifications : 
1- add <location name="/img" handler="images"/> like this :

<host name="default-host" alias="localhost">
                    <location name="/" handler="welcome-content"/>
                    <location name="/img" handler="images"/>
                    <filter-ref name="server-header"/>
                    <filter-ref name="x-powered-by-header"/>
                </host>

2_ add <file name="images" path="/tmp/" directory-listing="true"/>
like this : 

            <handlers>
                <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
                <file name="images" path="/tmp/" directory-listing="true"/>
            </handlers>
            
don't forget to change /tmp to the upload folder you did choise in uploadfileService.java 