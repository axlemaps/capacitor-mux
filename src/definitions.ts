export interface CapacitorMuxPlugin {
  uploadVideo(options: UploadVideo): Promise<{ success: boolean }>;
}

export interface UploadVideo {
  uploadUri: string;
  videoFile: string;
}
