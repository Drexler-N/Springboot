USE [master]
GO
/****** Object:  Database [VisitorAccessDB]    Script Date: 2023/02/19 13:33:02 ******/
CREATE DATABASE [VisitorAccessDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'VisitorAccessDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\VisitorAccessDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'VisitorAccessDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\VisitorAccessDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [VisitorAccessDB] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [VisitorAccessDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [VisitorAccessDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [VisitorAccessDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [VisitorAccessDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [VisitorAccessDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [VisitorAccessDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [VisitorAccessDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [VisitorAccessDB] SET  MULTI_USER 
GO
ALTER DATABASE [VisitorAccessDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [VisitorAccessDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [VisitorAccessDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [VisitorAccessDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [VisitorAccessDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [VisitorAccessDB] SET QUERY_STORE = OFF
GO
USE [VisitorAccessDB]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
USE [VisitorAccessDB]
GO
/****** Object:  User [SpringUser]    Script Date: 2023/02/19 13:33:02 ******/
CREATE USER [SpringUser] FOR LOGIN [SpringUser] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [NT AUTHORITY\SYSTEM]    Script Date: 2023/02/19 13:33:02 ******/
CREATE USER [NT AUTHORITY\SYSTEM] FOR LOGIN [NT AUTHORITY\SYSTEM] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_datareader] ADD MEMBER [SpringUser]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [SpringUser]
GO
ALTER ROLE [db_owner] ADD MEMBER [NT AUTHORITY\SYSTEM]
GO
/****** Object:  UserDefinedFunction [dbo].[udfTotalTenantFines]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[udfTotalTenantFines]
(
	@TenantID INT
)
RETURNS MONEY
AS
BEGIN
	DECLARE @Debt MONEY;

	SELECT @Debt = SUM(Amount)
		FROM FinedTenants
		WHERE TenantID = @TenantID;

	RETURN @Debt
END

	
GO
/****** Object:  Table [dbo].[FinedTenants]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FinedTenants](
	[FinedTenantsID] [int] IDENTITY(1,1) NOT NULL,
	[TenantID] [int] NOT NULL,
	[Amount] [money] NOT NULL,
 CONSTRAINT [PK_FinedTenants] PRIMARY KEY CLUSTERED 
(
	[FinedTenantsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[People]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[People](
	[PersonID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](120) NOT NULL,
	[LastName] [varchar](120) NOT NULL,
	[IDNumber] [varchar](13) NOT NULL,
	[TypeID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PersonID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UC_People] UNIQUE NONCLUSTERED 
(
	[IDNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tenants]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tenants](
	[TenantID] [int] IDENTITY(1,1) NOT NULL,
	[PersonID] [int] NOT NULL,
	[RoomNumber] [varchar](max) NOT NULL,
	[HasVisitor] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TenantID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Types]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Types](
	[TypeID] [int] IDENTITY(1,1) NOT NULL,
	[TypeName] [varchar](max) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Visitors]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Visitors](
	[VisitorID] [int] IDENTITY(1,1) NOT NULL,
	[PersonID] [int] NOT NULL,
	[TenantID] [int] NOT NULL,
	[DateIn] [datetime] NOT NULL,
	[DateOut] [datetime] NULL,
	[Inside] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[VisitorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[FinedTenants] ADD  DEFAULT ((100)) FOR [Amount]
GO
ALTER TABLE [dbo].[Tenants] ADD  DEFAULT ((0)) FOR [HasVisitor]
GO
ALTER TABLE [dbo].[Visitors] ADD  DEFAULT ((0)) FOR [Inside]
GO
ALTER TABLE [dbo].[FinedTenants]  WITH CHECK ADD FOREIGN KEY([TenantID])
REFERENCES [dbo].[Tenants] ([TenantID])
GO
ALTER TABLE [dbo].[People]  WITH CHECK ADD FOREIGN KEY([TypeID])
REFERENCES [dbo].[Types] ([TypeID])
GO
ALTER TABLE [dbo].[Tenants]  WITH CHECK ADD FOREIGN KEY([PersonID])
REFERENCES [dbo].[People] ([PersonID])
GO
ALTER TABLE [dbo].[Visitors]  WITH CHECK ADD FOREIGN KEY([PersonID])
REFERENCES [dbo].[People] ([PersonID])
GO
ALTER TABLE [dbo].[Visitors]  WITH CHECK ADD FOREIGN KEY([TenantID])
REFERENCES [dbo].[Tenants] ([TenantID])
GO
/****** Object:  StoredProcedure [dbo].[uspAddingTenant]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[uspAddingTenant]
	@FirstName VARCHAR(120),
	@LastName VARCHAR(120),
	@IDNumber VARCHAR(13),
	@RoomNumber VARCHAR(Max)
AS
BEGIN
	
	DECLARE @TypeID INT = 1;

	INSERT INTO People(
		[FirstName],
		[LastName],
		[IDNumber],
		[TypeID])

		VALUES (@FirstName, @LastName, @IDNumber, @TypeID)

	INSERT INTO Tenants(
		[PersonID],
		[RoomNumber])
		
		VALUES(
			(SELECT PersonID FROM People WHERE FirstName = @FirstName AND LastName = @LastName AND IDNumber = @IDNumber), @RoomNumber
			)

END
GO
/****** Object:  StoredProcedure [dbo].[uspEnteringVisitor]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[uspEnteringVisitor]
	@TenantID INT,
	@FirstName VARCHAR(120),
	@LastName VARCHAR(120),
	@IDNumber VARCHAR(13)
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @TypeID INT = 2;
	DECLARE @DateIn DATETIME = GETDATE();
	DECLARE @PersonID INT;

	INSERT INTO People(
		[FirstName],
		[LastName],
		[IDNumber],
		[TypeID])
		VALUES(@FirstName, @LastName, @IDNumber, @TypeID)

	SELECT @PersonID = PersonID FROM People
		WHERE FirstName = @FirstName AND LastName = @LastName AND IDNumber = @IDNumber

	INSERT INTO Visitors(
		[PersonID],
		[TenantID],
		[DateIn],
		[Inside])
		VALUES(@PersonID, @TenantID, @DateIn, 1)

	UPDATE Tenants
	SET HasVisitor = 1
	WHERE TenantID = @TenantID
END
GO
/****** Object:  StoredProcedure [dbo].[uspLeavingVisitor]    Script Date: 2023/02/19 13:33:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[uspLeavingVisitor]
	@VisitorID INT
AS
BEGIN
	DECLARE @CurrentDate DATETIME = GETDATE();
	DECLARE @NumberOfGuests INT;
	DECLARE @DateIn DATETIME;
	DECLARE @TenantID INT;


	SELECT @DateIn = DateIn, @TenantID = TenantID 
		FROM Visitors
		WHERE VisitorID = @VisitorID

	IF @CurrentDate > DATEADD(hour,24,@DateIn)
		INSERT INTO FinedTenants(
			[TenantID])
			VALUES (@TenantID)
	
	UPDATE Visitors SET Inside = 0, DateOut = @CurrentDate WHERE VisitorID = @VisitorID;

	SELECT @NumberOfGuests = COUNT(*) FROM Visitors WHERE TenantID = @TenantID AND Inside = 1;

	UPDATE Tenants SET HasVisitor = 0 WHERE TenantID = @TenantID AND @NumberOfGuests=0;	

END
GO
USE [master]
GO
ALTER DATABASE [VisitorAccessDB] SET  READ_WRITE 
GO
