Create database QL_GAME4

 CREATE TABLE [dbo].[TAIKHOAN](
	
	[User] [varchar](50) PRIMARY KEY ,
	[Password] [char](20) not null,
	[Quyen] int not null,
	[TrangThai] varchar(10),

)

CREATE TABLE [dbo].[CHEDO](
	
	[MaCD] [int] primary key,
	[TenCD] [nvarchar](50) not null,
	[TrangThai] varchar(10),
	[ThoiGian] int

)

CREATE TABLE [dbo].[DIEM](
	
	[MaTK] int IDENTITY(1,1) primary key ,
	[Player] [varchar](50),
	[MaCD] [int]  NOT NULL,
	[KETQUA] int ,
	[THOIGIAN] int ,
	FOREIGN KEY (MaCD) REFERENCES [CHEDO] ([MaCD]),
	FOREIGN KEY ([Player]) REFERENCES [TAIKHOAN] ([User])
)

INSERT [dbo].[TAIKHOAN]  VALUES ('admin', '123',0,'On')
INSERT [dbo].[TAIKHOAN]  VALUES ('moon', '123',1,'Off')
INSERT [dbo].[TAIKHOAN]  VALUES ('helo', '123',1,'Off')
INSERT [dbo].[TAIKHOAN]  VALUES ('yun', '123',1,'Off')
INSERT [dbo].[TAIKHOAN]  VALUES ('moon1', '123',1,'Off')
INSERT [dbo].[TAIKHOAN]  VALUES ('sun', '123',1,'Off')
INSERT [dbo].[TAIKHOAN]  VALUES ('woozi', '123',1,'Off')


INSERT INTO [dbo].[CHEDO] 
VALUES(1,N'Dễ','Off',60),(2,N'Trung Bình','Off',120),(3,N'Khó','Off',180); 

INSERT [dbo].[DIEM] ([Player],[MaCD],[KETQUA],[THOIGIAN]) VALUES ('moon',1,100,54)
INSERT [dbo].[DIEM] ([Player],[MaCD],[KETQUA],[THOIGIAN]) VALUES ('helo',1,240,67)
INSERT [dbo].[DIEM] ([Player],[MaCD],[KETQUA],[THOIGIAN]) VALUES ('yun',2,120,40)
INSERT [dbo].[DIEM] ([Player],[MaCD],[KETQUA],[THOIGIAN]) VALUES ('moon1',1,350,10)
INSERT [dbo].[DIEM] ([Player],[MaCD],[KETQUA],[THOIGIAN])VALUES ('sun',1,552,46)
INSERT [dbo].[DIEM] ([Player],[MaCD],[KETQUA],[THOIGIAN]) VALUES ('woozi',2,230,17)

INSERT INTO [dbo].[DIEM] ([Player],[MaCD],[KETQUA],[THOIGIAN])values('moon',1,156,23)


select [User] from TAIKHOAN where TrangThai='On'

select * from [DIEM]

UPDATE TaiKhoan SET [TrangThai] = 'Off' Where [User] = 'admin'

select Player, KETQUA, DIEM.THOIGIAN from DIEM, CHEDO where DIEM.MaCD = CHEDO.MaCD and DIEM.MaCD = 1 order by KETQUA DESC